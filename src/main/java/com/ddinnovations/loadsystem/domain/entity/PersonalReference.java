package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.dto.ReferenceDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PersonalReference {
    private String id;
    private List<Reference> reference;
    private String interaction;
    private ReferenceDTO referred;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;
}
