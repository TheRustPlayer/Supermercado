package com.supermercado.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private BigDecimal total;

    @Column(insertable = false, updatable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<DetalleTicket> detalles;

    public Ticket() {}
    // Getters y Setters
    public int getId() { return id; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public List<DetalleTicket> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleTicket> detalles) { this.detalles = detalles; }
}