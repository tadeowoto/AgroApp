package com.example.agroapp.model.dto;

import java.io.Serializable;
import java.util.Date;

public class LoteDto implements Serializable {
    private String nombre;
    private String cultivo;
    private double superficie_ha;
    private Date fecha_creacion;

    public LoteDto(String nombre, String cultivo, double superficie_ha, Date fecha_creacion) {
        this.nombre = nombre;
        this.cultivo = cultivo;
        this.superficie_ha = superficie_ha;
        this.fecha_creacion = fecha_creacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public double getSuperficie_ha() {
        return superficie_ha;
    }

    public void setSuperficie_ha(double superficie_ha) {
        this.superficie_ha = superficie_ha;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
