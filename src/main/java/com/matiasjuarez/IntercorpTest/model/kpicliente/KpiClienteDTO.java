package com.matiasjuarez.IntercorpTest.model.kpicliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
