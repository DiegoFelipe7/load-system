package com.ddinnovations.loadsystem.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Reference {
    private String id;
    private String name;
    private String phone;
    private PersonalReference personalReference;
}
