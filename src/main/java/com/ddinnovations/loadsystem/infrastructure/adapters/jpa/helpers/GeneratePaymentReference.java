package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers;

public class GeneratePaymentReference {
    private GeneratePaymentReference() {
        throw new IllegalStateException("Utility class");
    }

    public static String reference(String paymentReference) {
        String[] split = paymentReference.split("-N");
        int number = Integer.parseInt(split[1]) + 1;
        return split[0] + "-N" + number;
    }
}
