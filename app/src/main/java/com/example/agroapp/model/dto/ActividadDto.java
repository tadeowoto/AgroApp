package com.example.agroapp.model.dto;
import java.io.Serializable;
import java.util.Date;

public class ActividadDto  implements Serializable {
    public Integer id_lote;
    public Integer id_insumo;
    public Integer id_recurso;
    public Integer id_tipo_actividad;
    public String descripcion;
    public Date fecha_inicio;
    public Date fecha_fin;
    public double cantidad_insumo;
    public double costo;

    public Integer getId_lote() {
        return id_lote;
    }

    public void setId_lote(Integer id_lote) {
        this.id_lote = id_lote;
    }

    public Integer getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(Integer id_insumo) {
        this.id_insumo = id_insumo;
    }

    public Integer getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(Integer id_recurso) {
        this.id_recurso = id_recurso;
    }

    public Integer getId_tipo_actividad() {
        return id_tipo_actividad;
    }

    public void setId_tipo_actividad(Integer id_tipo_actividad) {
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

    public ActividadDto(Integer id_lote, Integer id_insumo, Integer id_recurso, Integer id_tipo_actividad, String descripcion, Date fecha_inicio, Date fecha_fin, double cantidad_insumo, double costo) {
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
}
