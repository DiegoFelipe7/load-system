package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalReferenceDtoRepository extends JpaRepository<PersonalReferenceEntity, String> {
}
