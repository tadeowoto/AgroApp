package com.example.agroapp.model.insumo;

import java.io.Serializable;
import java.util.Date;

public class Insumo implements Serializable {

    private int id_insumo;
    private String nombre;
    private String tipo;
    private String unidad;
    private double stock_actual;
    private Date fecha_vencimiento;


    public Insumo(int id_insumo, String nombre, String tipo, String unidad, double stock_actual, Date fecha_vencimiento) {
        this.id_insumo = id_insumo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidad = unidad;
        this.stock_actual = stock_actual;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(double stock_actual) {
        this.stock_actual = stock_actual;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}

