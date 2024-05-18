package com.ddinnovations.loadsystem.infrastructure.adapters.s3.service;

import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.enums.ActionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {
    @Value("${aws.bucketName}")
    private String bucket;
    private final S3Client s3Client;

    public String upload(String idFolder, MultipartFile file, ActionType actionType) {
        try {
            String key = getString(idFolder, file, actionType);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .contentType("application/pdf")
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            return String.format("https://cash-money-s3.s3.amazonaws.com/%s", key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(BusinessException.Type.ERROR_UPLOAD_FILE);
        }
    }

    private static String getString(String idFolder, MultipartFile file, ActionType actionType) {
        if (!Objects.equals(file.getContentType(), "application/pdf")) {
            throw new BusinessException(BusinessException.Type.ERROR_INVALID_FORMAT);
        }
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "-");
        String key = "";
        if (actionType.equals(ActionType.loan)) {
            key = String.format("documentation-loan/%s/%s", idFolder, fileName);
        } else {
            key = String.format("documentation-clients/%s/%s", idFolder, fileName);
        }
        return key;
    }

    public boolean doesObjectExists(String filename) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest
                    .builder()
                    .bucket(bucket)
                    .key(filename)
                    .build();
            s3Client.headObject(headObjectRequest);
            return true;
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                return false;
            }
            return false;
        }
    }

    @Async
    public void deleteFile(String filename) {
        String name = filename.split(".com/")[1];
        if (!doesObjectExists(name)) {
            throw new BusinessException(BusinessException.Type.ERROR_UPLOAD_NOT_FOUND);
        }
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(name)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (S3Exception e) {
            throw new BusinessException(BusinessException.Type.ERROR_BD);
        }
    }

}
