package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.control.panel;

import com.ddinnovations.loadsystem.domain.entity.ControlPanel;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.ControlPanelRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.ClientsDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.LoanDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.mongo.loan.application.LoanApplicationDtoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class ControlPanelRepositoryAdapter implements ControlPanelRepository {
    private final ClientsDtoRepository clientsDtoRepository;
    private final LoanApplicationDtoRepository loanApplicationDtoRepository;
    private final LoanDtoRepository loanDtoRepository;
    private final PaymentScheduleDtoRepository paymentScheduleDtoRepository;

    //TODO: MEJORAR EL CODIGO
    @Override
    public ResponseGlobal<ControlPanel> generateControlPanelReport() {
        Long totalClients = clientsDtoRepository.count();
        Long totalRequest = loanApplicationDtoRepository.count();
        Long totalLoansInArrears = paymentScheduleDtoRepository.findAll().stream().filter(ele -> ele.getPaymentStatus().equals(PaymentStatus.Mora)).count();
        Object object = loanDtoRepository.getLoanStatistics();
        if (object instanceof Object[] array) {
            BigDecimal totalInvestedCapital = (BigDecimal) array[0];
            Long totalActiveLoans = ((Number) array[1]).longValue();
            BigDecimal totalProfits = (BigDecimal) array[2];
            BigDecimal profitsCollected = (BigDecimal) array[3];
            Long loansPaid = ((Number) array[4]).longValue();
            return new ResponseGlobal<>(new ControlPanel(totalClients, totalRequest, totalInvestedCapital, totalActiveLoans, totalProfits, profitsCollected, loansPaid, totalLoansInArrears));
        }
        return new ResponseGlobal<>(new ControlPanel(totalClients, totalRequest, BigDecimal.ZERO, 0L, BigDecimal.ZERO, BigDecimal.ZERO, 0L, totalLoansInArrears));

    }
}
