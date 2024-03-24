package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.mapper;

import com.ddinnovations.loadsystem.domain.entity.Reference;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.PersonalReferenceEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference.ReferenceEntity;

public class ReferenceMapper {
    private ReferenceMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Reference referenceDtoAreference(ReferenceEntity reference) {
        return Reference.builder()
                .id(reference.getId())
                .name(reference.getName())
                .phone(reference.getPhone())
                .personalReference(PersonalReferenceMapper.personalReferenceDtoAPersonalReference(reference.getPersonalReference()))
                .build();
    }

    public static ReferenceEntity referenceAReferenceDto(Reference reference , PersonalReferenceEntity personalReference) {
        return ReferenceEntity.builder()
                .id(reference.getId())
                .name(reference.getName())
                .phone(reference.getPhone())
                .personalReference(personalReference)
                .build();
    }

}
