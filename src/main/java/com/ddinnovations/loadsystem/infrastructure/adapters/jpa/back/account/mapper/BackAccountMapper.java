package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.mapper;

import com.ddinnovations.loadsystem.domain.entity.BankAccount;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.back.account.BackAccountEntity;

public class BackAccountMapper {
    private BackAccountMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BankAccount bankAccountDtoABacKAccount(BackAccountEntity backAccount) {
        return BankAccount.builder()
                .id(backAccount.getId())
                .accountType(backAccount.getAccountType())
                .bank(backAccount.getBank())
                .name(backAccount.getName())
                .accountNumber(backAccount.getAccountNumber())
                .createdAt(backAccount.getCreatedAt())
                .updatedAt(backAccount.getUpdatedAt())
                .build();
    }

    public static BackAccountEntity bankAccountABacKAccountDto(BankAccount backAccount) {
        return BackAccountEntity.builder()
                .id(backAccount.getId())
                .accountType(backAccount.getAccountType())
                .bank(backAccount.getBank())
                .name(backAccount.getName())
                .accountNumber(backAccount.getAccountNumber())
                .createdAt(backAccount.getCreatedAt())
                .updatedAt(backAccount.getUpdatedAt())
                .build();
    }

}
