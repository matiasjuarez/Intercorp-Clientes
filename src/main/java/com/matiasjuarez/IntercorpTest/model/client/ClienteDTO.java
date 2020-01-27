package com.matiasjuarez.IntercorpTest.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    private Long id;
    @ApiModelProperty(required = true)
    private String nombre;
    @ApiModelProperty(required = true)
    private String apellido;
    private Integer edad;
    @ApiModelProperty(required = true)
    private Long fechaNacimiento;
    @ApiModelProperty(required = true)
    private String fechaNacimientoString;
    private Long fechaProbableMuerte;
    private String fechaProbableMuerteString;
}
