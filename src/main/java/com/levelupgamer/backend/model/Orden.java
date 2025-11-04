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
@Table(name = "Ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "items_json", columnDefinition = "TEXT")
    private String itemsJson;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "costoEnvio", nullable = false)
    private Double costoEnvio;

    @Column(name = "TotalPagar", nullable = false)
    private Double totalPagar;

    @Column(name = "FechaCompra", nullable = false)
    private LocalDateTime fechaCompra;

}