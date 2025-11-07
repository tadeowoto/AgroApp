package com.example.agroapp.model.cosecha;

import java.io.Serializable;
import java.util.Date;

public class Cosecha implements Serializable {
    private int id_cosecha;
    private int id_lote;
    private Date fecha_inicio;
    private Date fecha_fin;
    private double rendimiento;
    private String observaciones;

    public Cosecha(int id_cosecha, int id_lote, Date fecha_inicio, Date fecha_fin, double rendimiento, String observaciones) {
        this.id_cosecha = id_cosecha;
        this.id_lote = id_lote;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.rendimiento = rendimiento;
        this.observaciones = observaciones;
    }

    public int getId_cosecha() {
        return id_cosecha;
    }

    public void setId_cosecha(int id_cosecha) {
        this.id_cosecha = id_cosecha;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}


