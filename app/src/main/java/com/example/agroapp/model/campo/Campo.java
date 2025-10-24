package com.example.agroapp.model.campo;

import java.io.Serializable;

public class Campo implements Serializable {

    private int id_campo;
    private int id_usuario;
    private String nombre;
    private String ubicacion;
    private double extension_ha;
    private double longitud;
    private double latitud;

    public Campo(int id_campo, int id_usuario, String nombre, String ubicacion, double extension_ha, double longitud, double latitud) {
        this.id_campo = id_campo;
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.extension_ha = extension_ha;
        this.longitud = longitud;
        this.latitud = latitud;
    }
    public Campo(String nombre, String ubicacion, double extension_ha, double longitud, double latitud) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.extension_ha = extension_ha;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public int getId_campo() {
        return id_campo;
    }

    public void setId_campo(int id_campo) {
        this.id_campo = id_campo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getExtension_ha() {
        return extension_ha;
    }

    public void setExtension_ha(double extension_ha) {
        this.extension_ha = extension_ha;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}


