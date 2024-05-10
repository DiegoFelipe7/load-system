package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.control.panel.mapper;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

import java.math.BigDecimal;

public class ControlPanelMapper {
    private ControlPanelMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static ControlPanel controlPanelDto(Long totalClients, Long totalRequest, Long totalLoansInArrears, BigDecimal legalExpensesTotal, Object object) {
        if (object instanceof Object[] array) {
            BigDecimal totalInvestedCapital = (BigDecimal) array[0];
            Long totalActiveLoans = ((Number) array[1]).longValue();
            BigDecimal totalProfits = (BigDecimal) array[2];
            BigDecimal profitsCollected = (BigDecimal) array[3];
            Long loansPaid = ((Number) array[4]).longValue();
            return ControlPanel.builder()
                    .totalClients(totalClients)
                    .totalRequest(totalRequest)
                    .totalCapitalInvested(totalInvestedCapital)
                    .totalActiveLoans(totalActiveLoans)
                    .totalProfits(totalProfits)
                    .profitsCollected(profitsCollected)
                    .totalLoansPaid(loansPaid)
                    .totalLoansInArrears(totalLoansInArrears)
                    .legalExpensesTotal(legalExpensesTotal)
                    .build();
        }
        throw new BusinessException(BusinessException.Type.ERROR_BD);

    }
}

