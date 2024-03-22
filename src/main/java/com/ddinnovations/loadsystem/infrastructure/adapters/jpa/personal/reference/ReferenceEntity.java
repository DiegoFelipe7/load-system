package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.personal.reference;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "reference")
public class ReferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phone;
    @ManyToOne(targetEntity = PersonalReferenceEntity.class, fetch = FetchType.LAZY)
    private PersonalReferenceEntity personalReference;

}