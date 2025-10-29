package com.example.agroapp.model.lote;

import java.io.Serializable;
import java.util.Date;

public class Lote implements Serializable {

    private int id_lote;
    private int id_campo;
    private String nombre;
    private double superficie_ha;
    private String cultivo;

    private Date fecha_creacion;

    public Lote(int id_lote, Date fecha_creacion, String cultivo, double superficie_ha, String nombre, int id_campo) {
        this.id_lote = id_lote;
        this.fecha_creacion = fecha_creacion;
        this.cultivo = cultivo;
        this.superficie_ha = superficie_ha;
        this.nombre = nombre;
        this.id_campo = id_campo;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getId_campo() {
        return id_campo;
    }

    public void setId_campo(int id_campo) {
        this.id_campo = id_campo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSuperficie_ha() {
        return superficie_ha;
    }

    public void setSuperficie_ha(double superficie_ha) {
        this.superficie_ha = superficie_ha;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}

