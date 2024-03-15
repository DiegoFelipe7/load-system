package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers;

import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.enums.PaymentOfPayroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenerateCalendar {
    private GenerateCalendar() {
        throw new IllegalStateException("Utility class");
    }

    public static BigDecimal calculateFee(BigDecimal amount, double interest, int numberOfInstallments) {
        double interestRate = interest / 100.0;
        BigDecimal totalInterest = amount.multiply(BigDecimal.valueOf(interestRate)).multiply(BigDecimal.valueOf(numberOfInstallments));
        BigDecimal totalAmount = amount.add(totalInterest);
        return totalAmount.divide(BigDecimal.valueOf(numberOfInstallments), 0, RoundingMode.HALF_UP);
    }

    public static int calculateDaysBetweenPayments(PaymentOfPayroll paymentPeriod) {
        return switch (paymentPeriod) {
            case Mensual -> 30;
            case Quincenal -> 15;
            case Semanal -> 7;
            default -> throw new BusinessException(BusinessException.Type.ERROR_BD);
        };
    }

    public static Calendar generateCalendar(String valueDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(valueDate);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new BusinessException(BusinessException.Type.DATE_INVALID);
        }
    }


}
