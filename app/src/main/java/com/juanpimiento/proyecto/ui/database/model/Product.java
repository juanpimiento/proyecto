package com.juanpimiento.proyecto.ui.database.model;

import android.media.Image;

public class Product {
    private String identi;
    private String imagen;
    private String nombre;
    private String precio;
    private String descripcion;

    public Product() {
    }

    public Product(String identi, String imagen, String nombre, String precio, String descripcion) {
        this.identi = identi;
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getIdenti() {
        return identi;
    }

    public void setIdenti(String identi) {
        this.identi = identi;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
