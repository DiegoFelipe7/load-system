package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.annotations;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "annotations")
@Entity
public class AnnotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private String loanId;
    private LocalDateTime createdAt;
    private String searchKey;

    @PrePersist()
    public void insert() {
        this.searchKey = (this.title + '|' + this.description).toLowerCase();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate()
    public void update() {
        this.searchKey = (this.title + '|' + this.description).toLowerCase();
    }
}
