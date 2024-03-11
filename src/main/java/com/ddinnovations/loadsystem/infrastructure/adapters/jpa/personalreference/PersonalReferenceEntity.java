package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personalreference;

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
@Table(name = "personal-reference")
public class PersonalReferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    /*@ElementCollection
    @CollectionTable(name = "reference", joinColumns = @JoinColumn(name = "personal_reference_id"))
    private List<ReferenceDTO> reference;
    */
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
