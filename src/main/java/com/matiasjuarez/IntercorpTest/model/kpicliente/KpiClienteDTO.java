package com.matiasjuarez.IntercorpTest.model.kpicliente;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KpiClienteDTO {
    private Double promedioEdad;
    private Double desviacionEstandarEdad;

    public KpiClienteDTO(Double promedioEdad, Double desviacionEstandarEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandarEdad = desviacionEstandarEdad;
    }

    public KpiClienteDTO() {
    }
}
