package com.matiasjuarez.IntercorpTest.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private Long fechaNacimiento;
    private Long fechaProbableMuerte;
}
