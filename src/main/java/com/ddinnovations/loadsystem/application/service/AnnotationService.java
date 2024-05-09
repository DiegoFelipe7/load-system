package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.AnnotationUseCase;
import com.ddinnovations.loadsystem.domain.entity.Annotations;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsAnnotation;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.AnnotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnotationService implements AnnotationUseCase {
    private final AnnotationRepository annotationRepository;

    @Override
    public ResponseGlobalPagination<List<Annotations>> findAllAnnotations(String id, ParamsAnnotation params) {
        return annotationRepository.findAllAnnotations(id, params);
    }

    @Override
    public ResponseGlobal<Annotations> findByIdAnnotation(String id) {
        return annotationRepository.findByIdAnnotation(id);
    }

    @Override
    public ResponseGlobal<Annotations> createAnnotation(Annotations annotations) {
        return annotationRepository.createAnnotation(annotations);
    }

    @Override
    public ResponseGlobal<Annotations> update(String id, Annotations annotations) {
        return annotationRepository.update(id, annotations);
    }

    @Override
    public ResponseGlobal<Id> delete(String id) {
        return annotationRepository.delete(id);
    }
}
