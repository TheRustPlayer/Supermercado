package com.supermercado.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    private String telefono;
    private String email;
    private String direccion;

    @Column(unique = true, length = 20)
    private String cif;

    public Proveedor() {}
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }
}