package com.ddinnovations.loadsystem.domain.entity.enums;

public enum TypeOfIdentification {
    Cedula("Cedula"),
    Pasaporte("Pasaporte"),
    CedulaExtranjera("Cedula Extranjera");

    private final String description;

    TypeOfIdentification(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
