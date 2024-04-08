package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.loan;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanReportDto;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class LoanRepositoryAdapter extends AdapterOperations<Loan, LoanEntity, String, LoanDtoRepository> implements LoanRepository {

    protected LoanRepositoryAdapter(LoanDtoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Loan.LoanBuilder.class).build());
    }

    @Override
    @Transactional
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
        loanEntity.setNumberOfQuotas(loan.getNumberOfQuotas());
        loanEntity.setPaymentCycle(loan.getPaymentCycle());
        loanEntity.setFirstPaymentDate(loan.getFirstPaymentDate());
        loanEntity.setLoanState(LoanState.Aprobado);
        loanEntity.setEarnings(loanEntity.earnings());
        BigDecimal earnings = loanEntity.getEarnings().divide(BigDecimal.valueOf(loanEntity.getNumberOfQuotas()), RoundingMode.HALF_UP);
        generatePaymentSchedule(loan).getBody().forEach(ele -> {
            PaymentScheduleEntity paymentSchedule = PaymentScheduleMapper.paymentScheduleAPaymentScheduleDto(earnings, ele, loanEntity);
            loanEntity.getPaymentSchedule().add(paymentSchedule);
        });


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

    public ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(Loan loan) {
        List<PaymentSchedule> paymentSchedules = new ArrayList<>();
        Calendar calendar = GenerateCalendar.generateCalendar(loan.getFirstPaymentDate());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal amount = GenerateCalendar.calculateFee(loan.getAmount(), loan.getInterest(), loan.getDeadline(), loan.getNumberOfQuotas());
        for (int i = 0; i < loan.getNumberOfQuotas(); i++) {
            Date expirationDate = calendar.getTime();

            paymentSchedules.add(PaymentSchedule.builder()
                    .paymentDate(dateFormat.format(expirationDate))
                    .amount(amount)
                    .quotaNumber(i + 1)
                    .paymentCycle(loan.getPaymentCycle())
                    .paymentStatus(PaymentStatus.Pendiente)
                    .build());

            calendar.add(Calendar.DAY_OF_WEEK, GenerateCalendar.calculateDaysBetweenPayments(loan.getPaymentCycle()));
        }
        return new ResponseGlobal<>(paymentSchedules);
    }

    @Override
    public ResponseGlobal<Loan> findByIdLoan(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        return new ResponseGlobal<>(LoanMapper.loanDtoALoansAPaymentSchedule(loanEntity));
    }

    @Override
    @Transactional
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
            BigDecimal earnings = (BigDecimal) array[2];
            Long totalActiveLoans = ((Number) array[3]).longValue();
            Long activeLoans = ((Number) array[4]).longValue();
            Long totalLoansPaid = ((Number) array[5]).longValue();
            Long loansPaid = ((Number) array[6]).longValue();
            return new ResponseGlobal<>(new LoanIndicatorDTO(totalInvestedCapital, investedCapital, earnings, totalActiveLoans, activeLoans, totalLoansPaid, loansPaid));
        }
        return new ResponseGlobal<>(new LoanIndicatorDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L, 0L, 0L, 0L));
    }

    @Override
    public byte[] loanReport(String id, String paymentId) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        PaymentScheduleEntity paymentSchedule = loanEntity.paymentSchedule(paymentId);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("PaymentReference", paymentSchedule.getPaymentReference());
            params.put("FullName", loanEntity.getClient().getFullName());
            params.put("Phone", loanEntity.getClient().getPhone());
            params.put("Address", loanEntity.getClient().getAddress());
            params.put("Balance", loanEntity.balance());
            params.put("TotalLoan", loanEntity.getAmount());
            params.put("ValuePaid", loanEntity.valuePaid());
            params.put("ImageDir", "classpath:/static/images/");
            params.put("PaymentNumber", loanEntity.getNumberOfPayments() + "/" + loanEntity.getNumberOfQuotas());
            params.put("QuotaValue", paymentSchedule.getAmount());
            params.put("Total", paymentSchedule.totalPayment());
            params.put("OutstandingBalance", paymentSchedule.getOutstandingBalance());
            InputStream reportStream = getClass().getResourceAsStream("/LoanApplicationReport.jrxml");
            JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(reportStream), params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(report);
        } catch (Exception e) {
            throw new BusinessException(BusinessException.Type.ERROR_BD);
        }
    }

    @Override
    public ResponseGlobal<Id> removeLoan(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        repository.deleteById(loanEntity.getId());
        return new ResponseGlobal<>(new Id(loanEntity.getId()));
    }


    private LoanEntity getByIdLoan(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.LOAN_NOT_FOUND));
    }

    public void updatePaymentNumber(String id) {
        LoanEntity loanEntity = this.getByIdLoan(id);
        loanEntity.setNumberOfPayments(loanEntity.getNumberOfPayments() + 1);
        if (loanEntity.getNumberOfPayments() == loanEntity.getNumberOfQuotas()) {
            loanEntity.setLoanState(LoanState.Pagado);
            repository.save(loanEntity);
        }
        repository.save(loanEntity);
    }

}