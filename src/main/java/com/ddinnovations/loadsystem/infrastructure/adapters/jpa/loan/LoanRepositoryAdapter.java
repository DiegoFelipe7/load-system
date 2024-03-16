package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanReportDTO;
import com.ddinnovations.loadsystem.domain.entity.enums.LoanState;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentStatus;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.LoanRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.LoanSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateCalendar;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.GenerateDates;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan.mapper.LoanMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.PaymentScheduleEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.payment.schedule.mapper.PaymentScheduleMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class LoanRepositoryAdapter extends AdapterOperations<Loan, LoanEntity, String, LoanDtoRepository> implements LoanRepository {

    protected LoanRepositoryAdapter(LoanDtoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Loan.LoanBuilder.class).build());
    }

    @Override
    public ResponseGlobal<Loan> createLoan(Loan loan) {
        LoanEntity loanEntity = LoanMapper.loanALoanDto(loan);
        return new ResponseGlobal<>(LoanMapper.loanDtoALoan(repository.save(loanEntity)));
    }

    @Override
    @Transactional
    public ResponseGlobal<Loan> approveLoan(String id, Loan loan) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        loanEntity.setAmount(loan.getAmount());
        loanEntity.setInterest(loan.getInterest());
        loanEntity.setDeadline(loan.getDeadline());
        loanEntity.setPaymentCycle(loan.getPaymentCycle());
        loanEntity.setFirstPaymentDate(loan.getFirstPaymentDate());
        loanEntity.setLoanState(LoanState.Aprobado);
        generatePaymentSchedule(loan).getBody().forEach(ele -> {
            PaymentScheduleEntity paymentSchedule = PaymentScheduleMapper.paymentScheduleAPaymentScheduleDto(ele, loanEntity);
            loanEntity.getPaymentSchedule().add(paymentSchedule);
        });
        loanEntity.setEarnings(loanEntity.earnings());

        return new ResponseGlobal<>(LoanMapper.loanDtoALoansAPaymentSchedule(repository.save(loanEntity)));
    }

    @Override
    public ResponseGlobalPagination<List<Loan>> findAllLoan(ParamsLoan params) {
        LoanSpecification specification = new LoanSpecification(params.getFilterCriteriaText(), params.getPaymentCycle(), params.getLoanState(), params.getStartDate());
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit(), params.getSort());
        List<Loan> loanApplications = repository.findAll(specification, pages)
                .stream()
                .map(LoanMapper::loanDtoALoan)
                .toList();
        return new ResponseGlobalPagination<>(loanApplications, new Pagination(params.getPage(), params.getLimit(), ((int) repository.count())));

    }

    @Override
    public ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(Loan loan) {
        List<PaymentSchedule> paymentSchedules = new ArrayList<>();
        Calendar calendar = GenerateCalendar.generateCalendar(loan.getFirstPaymentDate());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal amount = GenerateCalendar.calculateFee(loan.getAmount(), loan.getInterest(), loan.getDeadline());
        for (int i = 0; i < loan.getDeadline(); i++) {
            calendar.add(Calendar.DAY_OF_WEEK, GenerateCalendar.calculateDaysBetweenPayments(loan.getPaymentCycle()));
            Date expirationDate = calendar.getTime();
            paymentSchedules.add(new PaymentSchedule(dateFormat.format(expirationDate), amount, i + 1, loan.getPaymentCycle(), PaymentStatus.Pendiente));
        }
        return new ResponseGlobal<>(paymentSchedules);
    }


    @Override
    public ResponseGlobal<Loan> findByIdLoan(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        return new ResponseGlobal<>(LoanMapper.loanDtoALoansAPaymentSchedule(loanEntity));
    }

    @Override
    public ResponseGlobal<Loan> cancelLoan(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        loanEntity.setLoanState(LoanState.Cancelado);
        loanEntity.getPaymentSchedule().forEach(ele -> ele.setPaymentStatus(PaymentStatus.Cancelado));
        return new ResponseGlobal<>(LoanMapper.loanDtoALoan(repository.save(loanEntity)));
    }

    @Override
    public ResponseGlobal<LoanIndicatorDTO> loanIndicators() {
        Object object = repository.getIndicators(GenerateDates.starDateFilter(), GenerateDates.endDateFilter());
        if (object instanceof Object[] array) {
            BigDecimal totalInvestedCapital = (BigDecimal) array[0];
            BigDecimal investedCapital = (BigDecimal) array[1];
            Long totalActiveLoans = ((Number) array[2]).longValue();
            Long activeLoans = ((Number) array[3]).longValue();
            Long totalLoansPaid = ((Number) array[4]).longValue();
            Long loansPaid = ((Number) array[5]).longValue();
            return new ResponseGlobal<>(new LoanIndicatorDTO(totalInvestedCapital, investedCapital, totalActiveLoans, activeLoans, totalLoansPaid, loansPaid));
        }
        return new ResponseGlobal<>(new LoanIndicatorDTO(BigDecimal.ZERO, BigDecimal.ZERO, 0L, 0L, 0L, 0L));
    }

    @Override
    public byte[] loanReport(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        LoanReportDTO loanReportDTO = LoanMapper.loanReportDTO(loanEntity);
        return new byte[0];
    }

    /**
     * SELECT
     * SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.amount ELSE 0 END) AS totalCapitalInvertido,
     * SUM(CASE WHEN l.loan_state IN (1) THEN 1 ELSE 0 END) AS totalPrestamosActivos,
     * SUM(CASE WHEN l.loan_state IN (1, 3) THEN l.earnings ELSE 0 END) AS totalGanancias,
     * SUM(CASE WHEN l.loan_state IN (3) THEN 1 ELSE 0 END) AS totalPrestamosPagados
     * FROM   loan l
     *
     * @param id
     * @return
     */

    private LoanEntity getByIdLoan(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.LOAN_NOT_FOUND));
    }

    public void updatePaymentNumber(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        loanEntity.setNumberOfPayments(loanEntity.getNumberOfPayments() + 1);
        if (loanEntity.getNumberOfPayments() == loanEntity.getDeadline()) {
            loanEntity.setLoanState(LoanState.Pagado);
            repository.save(loanEntity);
        }
        repository.save(loanEntity);
    }

}