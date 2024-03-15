package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerateDates {
    private GenerateDates() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime starDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 00:00:00", formatter);
    }


    public static LocalDateTime endDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 23:59:00", formatter);

    }


    public static LocalDateTime starDateFilter() {
        return LocalDateTime.now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withDayOfMonth(1);

    }

    public static LocalDateTime endDateFilter() {
        return LocalDateTime.now()
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0);

    }

}
