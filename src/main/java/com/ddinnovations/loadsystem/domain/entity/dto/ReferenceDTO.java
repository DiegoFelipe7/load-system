package com.ddinnovations.loadsystem.domain.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ReferenceDTO {
    private String name;
    private String phone;
}
