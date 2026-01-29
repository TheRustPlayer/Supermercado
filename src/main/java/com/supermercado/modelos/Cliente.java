package com.supermercado.modelos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //
    
    private String nombre; //
    private String apellido; //
    private String dni; //
    private String telefono; //
    private String email; //
    
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro; //

    private String usuario;
    private String password;
    private boolean activo = true;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;    }
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Date getFechaRegistro() {return fechaRegistro;}
    public void setFechaRegistro(Date fechaRegistro) {this.fechaRegistro = fechaRegistro;}
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public boolean isActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}
    
}