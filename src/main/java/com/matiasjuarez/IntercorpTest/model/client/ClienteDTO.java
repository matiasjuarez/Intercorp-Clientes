package com.matiasjuarez.IntercorpTest.model.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private Long fechaNacimiento;
    private Long fechaProbableMuerte;
}
