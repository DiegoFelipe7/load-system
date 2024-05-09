package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations.mapper;

import com.ddinnovations.loadsystem.domain.entity.Annotations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations.AnnotationEntity;

public class AnnotationMapper {

    private AnnotationMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AnnotationEntity annotationDtoAAnnotation(Annotations annotation) {
        return AnnotationEntity.builder()
                .id(annotation.getId())
                .title(annotation.getTitle())
                .loanId(annotation.getLoanId())
                .description(annotation.getDescription())
                .build();
    }

    public static Annotations annotationAAnnotationDto(AnnotationEntity annotation) {
        return Annotations.builder()
                .id(annotation.getId())
                .title(annotation.getTitle())
                .description(annotation.getDescription())
                .loanId(annotation.getLoanId())
                .createdAt(annotation.getCreatedAt())
                .build();
    }
}
