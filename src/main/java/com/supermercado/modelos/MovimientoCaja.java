package com.supermercado.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_caja")
public class MovimientoCaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    private BigDecimal cantidad;

    @Column(insertable = false, updatable = false)
    private LocalDateTime fecha;

    public MovimientoCaja() {}
}