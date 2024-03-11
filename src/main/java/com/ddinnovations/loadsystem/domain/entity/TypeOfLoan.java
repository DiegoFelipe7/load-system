package com.ddinnovations.loadsystem.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class TypeOfLoan {
    private String id;
    private String name;
    private double interestRateMin;
    private double interestRateMax;
    private String searchKey;
    private boolean state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
