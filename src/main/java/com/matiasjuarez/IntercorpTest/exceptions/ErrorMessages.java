package com.matiasjuarez.IntercorpTest.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ErrorMessages {
    @Getter(AccessLevel.NONE)
    private String CLIENTE_FIELD_REQUIRED_MODEL;
    private String clienteNameRequired;
    private String clienteLastnameRequired;
    private String clienteBirthdayRequired;
    private String clienteAmbiguousBirthdayInformation;

    public ErrorMessages() {
        CLIENTE_FIELD_REQUIRED_MODEL = "%s del cliente es requerido";
        clienteNameRequired = String.format(CLIENTE_FIELD_REQUIRED_MODEL, "El nombre");
        clienteLastnameRequired = String.format(CLIENTE_FIELD_REQUIRED_MODEL, "El apellido");
        clienteBirthdayRequired = String.format(CLIENTE_FIELD_REQUIRED_MODEL, "La fecha de nacimiento");
        clienteAmbiguousBirthdayInformation = "Se recibieron dos campos que representan la fecha de nacimiento del cliente";
    }
}
