package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.Loan;
import com.ddinnovations.loadsystem.domain.entity.PaymentSchedule;
import com.ddinnovations.loadsystem.domain.entity.dto.Id;
import com.ddinnovations.loadsystem.domain.entity.dto.LoanIndicatorDTO;
import com.ddinnovations.loadsystem.domain.entity.enums.FileType;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsLoan;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoanRepository {
    ResponseGlobal<Loan> createLoan(Loan loan);

    ResponseGlobal<Loan> approveLoan(String id, Loan loan);

    ResponseGlobalPagination<List<Loan>> findAllLoan(ParamsLoan params);

    ResponseGlobal<List<PaymentSchedule>> generatePaymentSchedule(Loan loan);

    ResponseGlobal<Loan> findByIdLoan(String id);

    ResponseGlobal<Loan> cancelLoan(String id);

    ResponseGlobal<LoanIndicatorDTO> loanIndicators();

    byte[] loanReport(String id, String paymentId);

    ResponseGlobal<Id> removeLoan(String id);

    ResponseGlobal<String> documentUpload(String id, FileType fileType, MultipartFile file);
    ResponseGlobal<String> updateDocumentUpload(String id, FileType fileType, MultipartFile file);

}
