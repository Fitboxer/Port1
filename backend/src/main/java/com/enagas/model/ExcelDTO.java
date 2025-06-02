package com.enagas.model;

import java.math.BigDecimal;

public class ExcelDTO {

    private String fecha_movimiento;
    private String infraestructura;
    private String observaciones;
    private String descripcion;
    private BigDecimal valor;
    private String justificante;
    private String observacionesAEAT;

    public ExcelDTO() {
    }

    public ExcelDTO(String fecha_movimiento, String infraestructura, String observaciones,
                    String descripcion, BigDecimal valor, String justificante, String observacionesAEAT) {
        this.fecha_movimiento = fecha_movimiento;
        this.infraestructura = infraestructura;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.valor = valor;
        this.justificante = justificante;
        this.observacionesAEAT = observacionesAEAT;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getJustificante() {
        return justificante;
    }

    public void setJustificante(String justificante) {
        this.justificante = justificante;
    }

    public String getObservacionesAEAT() {
        return observacionesAEAT;
    }

    public void setObservacionesAEAT(String observacionesAEAT) {
        this.observacionesAEAT = observacionesAEAT;
    }

}
