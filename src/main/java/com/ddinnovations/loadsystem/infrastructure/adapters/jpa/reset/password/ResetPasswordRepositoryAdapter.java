package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.reset.password;

import com.ddinnovations.loadsystem.domain.entity.ResetPassword;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;
import com.ddinnovations.loadsystem.domain.entity.enums.EmailTemplate;
import com.ddinnovations.loadsystem.domain.repository.ResetPasswordRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.AuthDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.reset.password.mapper.ResetPasswordMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.mail.service.EmailService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class ResetPasswordRepositoryAdapter extends AdapterOperations<ResetPassword, ResetPasswordEntity, String, ResetPasswordDtoRepository> implements ResetPasswordRepository {
    private final AuthDtoRepository authDtoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    protected ResetPasswordRepositoryAdapter(ResetPasswordDtoRepository repository, ObjectMapper mapper, AuthDtoRepository authDtoRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        super(repository, mapper, d -> mapper.map(d, ResetPassword.ResetPasswordBuilder.class).build());
        this.authDtoRepository = authDtoRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void forgotPassword(String email) {
        UserEntity userEntity = authDtoRepository.findByEmailEqualsIgnoreCase(email)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));

        userEntity.setState(false);
        authDtoRepository.save(userEntity);

        ResetPasswordEntity resetPassword = repository.save(ResetPasswordMapper.buildResetPassword(email));
        emailService.sendEmailWelcome(userEntity.getFirstName(), resetPassword.getToken(), resetPassword.getEmail(), EmailTemplate.WELCOME);
    }

    @Override
    public void changePassword(String token, ConfirmPasswordDTO confirmPasswordDTO) {
        ResetPasswordEntity resetPassword = repository.findByToken(token)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.TOKEN_INVALID));

        if (resetPassword.getExpirationDate().isAfter(LocalDateTime.now()) || !resetPassword.isState()) {
            throw new BusinessException(BusinessException.Type.TOKEN_EXPIRATION);
        }

        UserEntity userEntity = authDtoRepository.findByEmailEqualsIgnoreCase(resetPassword.getEmail())
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));
        userEntity.setState(Boolean.TRUE);
        userEntity.setPassword(passwordEncoder.encode(confirmPasswordDTO.password()));
        resetPassword.setState(false);

        repository.save(resetPassword);
        authDtoRepository.save(userEntity);
    }
}
