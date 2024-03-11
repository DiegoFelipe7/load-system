package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateDates {
    private GenerateDates() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDate convertDate(String date) {
        return LocalDate.parse(date.concat(" 00:00"), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
