package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personalreference;

import com.ddinnovations.loadsystem.domain.entity.PersonalReference;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class PersonalReferenceAdapter extends AdapterOperations<PersonalReference, PersonalReferenceEntity, String, PersonalReferenceDtoRepository> {

    protected PersonalReferenceAdapter(PersonalReferenceDtoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, PersonalReference.PersonalReferenceBuilder.class).build());
    }
}
