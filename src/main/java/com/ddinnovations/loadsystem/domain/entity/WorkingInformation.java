package com.ddinnovations.loadsystem.domain.entity;

import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class WorkingInformation {
    private String id;
    private String companyName;
    private String phone;
    private String address;
    private int timeWorking;
    private String position;
    private String bossName;
    private String bossPhone;
    private BigDecimal salary;
    private PaymentOfPayroll paymentOfPayroll;
    private BigDecimal otherIncome;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;




}
