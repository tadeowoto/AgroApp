package com.example.agroapp.model.tipoActividad;

import java.io.Serializable;

public class TipoActividad implements Serializable {
    private int id_tipo_actividad;
    private String nombre;
    private String descripcion;

    public TipoActividad(int id_tipo_actividad, String nombre, String descripcion) {
        this.id_tipo_actividad = id_tipo_actividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_tipo_actividad() {
        return id_tipo_actividad;
    }

    public void setId_tipo_actividad(int id_tipo_actividad) {
        this.id_tipo_actividad = id_tipo_actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
