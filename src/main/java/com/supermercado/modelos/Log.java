package com.supermercado.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación: Un log pertenece a un empleado (el que hizo la acción)
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private String accion; // Ej: "Inicio de sesión", "Eliminó empleado X"

    private LocalDateTime fecha;

    public Log() {}
    
    // Constructor rápido
    public Log(Empleado empleado, String accion) {
        this.empleado = empleado;
        this.accion = accion;
        this.fecha = LocalDateTime.now(); // Pone la fecha actual automáticamente
    }

    // Getters y Setters
    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}