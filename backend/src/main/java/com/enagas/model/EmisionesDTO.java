package com.enagas.model;

import java.math.BigDecimal;

public class EmisionesDTO {

    private BigDecimal emisiones;
    private BigDecimal factorEmision;
    private BigDecimal PCS;

    public EmisionesDTO() {
    }

    public EmisionesDTO(BigDecimal emisiones, BigDecimal factorEmision, BigDecimal PCS) {
        this.emisiones = emisiones;
        this.factorEmision = factorEmision;
        this.PCS = PCS;
    }

    public BigDecimal getEmisiones() {
        return emisiones;
    }

    public void setEmisiones(BigDecimal emisiones) {
        this.emisiones = emisiones;
    }

    public BigDecimal getFactorEmision() {
        return factorEmision;
    }

    public void setFactorEmision(BigDecimal factorEmision) {
        this.factorEmision = factorEmision;
    }

    public BigDecimal getPCS() {
        return PCS;
    }

    public void setPCS(BigDecimal PCS) {
        this.PCS = PCS;
    }
}
