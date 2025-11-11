package com.example.agroapp.model.precioGrano;

public class PrecioGrano {
    private String nombre;
    private String unidad;
    private double precio;
    private String icono;

    public PrecioGrano(String nombre, String unidad, double precio, String icono) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.precio = precio;
        this.icono = icono;
    }

    public String getNombre() { return nombre; }
    public String getUnidad() { return unidad; }
    public double getPrecio() { return precio; }
    public String getIcono() { return icono; }
}