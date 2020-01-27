package com.matiasjuarez.IntercorpTest.model.kpicliente;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KpiClienteDTO {
    @ApiModelProperty(notes = "The average of the ages of the Cliente entities", example = "25.5")
    private Double promedioEdad;
    @ApiModelProperty(notes = "The standard deviation of the ages of the Cliente entities", example = "1")
    private Double desviacionEstandarEdad;

    public KpiClienteDTO(Double promedioEdad, Double desviacionEstandarEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandarEdad = desviacionEstandarEdad;
    }

    public KpiClienteDTO() {
    }
}
