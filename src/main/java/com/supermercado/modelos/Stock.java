package com.supermercado.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    private int producto_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

    public Stock() {}
    // Getters y Setters
    public int getProducto_id() { return producto_id; }
    public void setProducto_id(int producto_id) { this.producto_id = producto_id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}