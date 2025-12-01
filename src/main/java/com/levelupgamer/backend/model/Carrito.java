package com.levelupgamer.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Carrito_orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "items_json", columnDefinition = "TEXT")
    private String itemsJson;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "costo_envio", nullable = false)
    private Double costoEnvio;

    @Column(name = "total_pagar", nullable = false)
    private Double totalPagar;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

}