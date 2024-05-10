package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.control.panel;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.ControlPanelRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.control.panel.mapper.ControlPanelMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application.LoanApplicationDtoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ControlPanelRepositoryAdapter implements ControlPanelRepository {
    private final ClientsDtoRepository clientsDtoRepository;
    private final LoanApplicationDtoRepository loanApplicationDtoRepository;
    private final LoanDtoRepository loanDtoRepository;
    private final PaymentScheduleDtoRepository paymentScheduleDtoRepository;

    @Override
    public ResponseGlobal<ControlPanel> generateControlPanelReport() {
        Long totalClients = clientsDtoRepository.count();
        Long totalRequest = loanApplicationDtoRepository.count();
        Long totalLoansInArrears = paymentScheduleDtoRepository.countByPaymentStatus(PaymentStatus.Mora);
        BigDecimal legalExpensesTotal = loanDtoRepository.findByLegalExpensesSum(GenerateDates.starDateFilter(), GenerateDates.endDateFilter());
        Object object = loanDtoRepository.getLoanStatistics();
        return new ResponseGlobal<>(ControlPanelMapper.controlPanelDto(totalClients, totalRequest, totalLoansInArrears, legalExpensesTotal, object));

    }

    @Override
    public ResponseGlobal<BigDecimal> generateLegalExpenses(String startDate, String endDate) {
        return new ResponseGlobal<>(this.loanDtoRepository.findByLegalExpensesSum(GenerateDates.starDate(startDate), GenerateDates.endDate(startDate)));
    }
}
