package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference;

import com.ddinnovations.loadsystem.domain.entity.dto.ReferenceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "personal_reference")
public class PersonalReferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(targetEntity = ReferenceEntity.class, fetch = FetchType.LAZY, mappedBy = "personalReference" , cascade = CascadeType.ALL)
    private List<ReferenceEntity> referenceEntity;
    private String interaction;
    @Embedded
    private ReferenceDTO referred;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist()
    public void insert() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
