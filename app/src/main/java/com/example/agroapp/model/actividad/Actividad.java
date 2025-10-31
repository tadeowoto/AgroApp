package com.example.agroapp.model.actividad;

import java.io.Serializable;
import java.util.Date;

public class Actividad implements Serializable {

    private int idActividad;
    private int id_lote;

    private int id_insumo;
    private int id_recurso;

    private int id_tipo_actividad;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private double cantidad_insumo;
    private double costo;


    public Actividad(int idActividad, int id_lote, int id_insumo, int id_recurso, int id_tipo_actividad, String descripcion, Date fecha_inicio, Date fecha_fin, double cantidad_insumo, double costo) {
        this.idActividad = idActividad;
        this.id_lote = id_lote;
        this.id_insumo = id_insumo;
        this.id_recurso = id_recurso;
        this.id_tipo_actividad = id_tipo_actividad;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.cantidad_insumo = cantidad_insumo;
        this.costo = costo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public int getId_tipo_actividad() {
        return id_tipo_actividad;
    }

    public void setId_tipo_actividad(int id_tipo_actividad) {
        this.id_tipo_actividad = id_tipo_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public double getCantidad_insumo() {
        return cantidad_insumo;
    }

    public void setCantidad_insumo(double cantidad_insumo) {
        this.cantidad_insumo = cantidad_insumo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}


/*

    {
        "idActividad": 1,
        "id_lote": 1,
        "id_insumo": 1,
        "id_recurso": 1,
        "id_tipo_actividad": 2,
        "descripcion": "Fertilización inicial con NPK",
        "fecha_inicio": "2025-09-15T08:00:00",
        "fecha_fin": "2025-09-15T15:00:00",
        "cantidad_insumo": 500.00,
        "costo": 12500.00
    },
 */