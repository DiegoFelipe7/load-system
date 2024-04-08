package com.ddinnovations.loadsystem.domain.entity.common;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public enum Type {
        ERROR_BD("ERROR EN LA BASE DE DATOS"),
        PASSWORD_INVALID("LA CONTRASEÑA NO ES CORRECTA"),
        DATE_INVALID("EL FORMATO DE LAS FECHA DEBE SER YYYY-DD-MM"),
        USER_EXIST("EL USUARIO SE ENCUENTRA REGISTRADO EN LA BASE DE DATOS"),
        USER_IDENTIFICATION("LA IDENTIFICACION DEL USUARIO YA SE ENCUENTRA REGISTRADA"),
        USER_NOT_EXIST("EL USUARIO NO SE ENCUENTRA REGISTRADO EN LA BASE DE DATOS"),
        INACTIVE_USER("EL USUARIO SE ENCUENTRA INACTIVO"),
        TOKEN_INVALID("EL TOKEN ES INVALIDO"),
        TOKEN_EXPIRATION("EL TOKEN YA EXPIRO SOLICITA UN NUEVO CAMBIO DE CONTRASEÑA"),
        LOAN_NOT_FOUND("EL PRESTAMO NO SE ENCUENTRA REGISTRADA EN EL SISTEMA"),
        LOAN_APPLICATION_NOT_FOUND("LA SOLICITUD DE PRESTAMO NO SE ENCUENTRA REGISTRADA EN EL SISTEMA"),
        THERE_IS_A_LOAN_APPLICATION("YA TE ENCUENTRAS REGISTRADO,POR FAVOR ACERCATE A LA OFICINA MAS CERCAR PARA LA ACTUALIZACION DE TUS DATOS"),
        PAYMENT_MADE("LA CUOTA YA SE ENCUENTRA PAGADA"),
        PAYMENT_INVALID("DEBES REALIZAR EL PAGO TOTAL DE LA COUTA,NO EXISTEN MAS COUTAS PARA ASIGNAR EL PAGO PENDIENTE"),
        INVALID_PAYMENT_REFERENCE("LA REFERENCIA DE PAGO NO EXISTE EN LA BD"),
        a("EL NUMERO DE");


        private final String message;


        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }


        Type(String message) {
            this.message = message;

        }

    }

    private final Type type;

    public BusinessException(Type type) {
        super(type.message);
        this.type = type;

    }


    @Override
    public String toString() {
        return "BusinessException{" +
                "type=" + type +
                '}';
    }
}
