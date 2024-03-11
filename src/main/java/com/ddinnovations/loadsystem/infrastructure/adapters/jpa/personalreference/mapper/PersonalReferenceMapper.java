package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personalreference.mapper;

import com.ddinnovations.loadsystem.domain.entity.PersonalReference;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personalreference.PersonalReferenceEntity;

public class PersonalReferenceMapper {
    private PersonalReferenceMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PersonalReference personalReferenceDtoAPersonalReference(PersonalReferenceEntity personalReference){
       return PersonalReference.builder()
                .id(personalReference.getId())
                .interaction(personalReference.getInteraction())
                .referred(personalReference.getReferred())
                .createdAt(personalReference.getCreatedAt())
                .updatedAt(personalReference.getUpdatedAt())
                .build();
    }
    public static PersonalReferenceEntity personalReferenceAPersonalReferenceDto(PersonalReference personalReference){
        return PersonalReferenceEntity.builder()
                .id(personalReference.getId())
                .interaction(personalReference.getInteraction())
                .referred(personalReference.getReferred())
                .createdAt(personalReference.getCreatedAt())
                .updatedAt(personalReference.getUpdatedAt())
                .build();
    }
}
