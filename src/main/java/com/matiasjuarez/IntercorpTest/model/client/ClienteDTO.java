package com.matiasjuarez.IntercorpTest.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(required = true, notes = "The name of the Cliente", example = "Matias")
    private String nombre;
    @ApiModelProperty(required = true, notes = "The lastname of the Cliente", example = "Juarez")
    private String apellido;
    @ApiModelProperty(notes = "The age of the Cliente", hidden = true)
    private Integer edad;
    @ApiModelProperty(required = true, notes = "The birthday expressed as Unix Timestamp", example = "671425200000")
    private Long fechaNacimiento;
    @ApiModelProperty(required = true, notes = "The birthday expressed as a String with format dd/MM/yyyy", example = "12/04/1991")
    private String fechaNacimientoString;
    @ApiModelProperty(notes = "The estimated date of death as Unix Timestamp", hidden = true)
    private Long fechaProbableMuerte;
    @ApiModelProperty(notes = "The estimated date of death as a String with format dd/MM/yyyy", hidden = true)
    private String fechaProbableMuerteString;
}
