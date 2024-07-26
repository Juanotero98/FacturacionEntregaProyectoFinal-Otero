package com.example.ecommercefinal.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "comprobantes")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del comprobante", example = "3")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Schema(description = "Datos del cliente")
    private Cliente cliente;
    @Schema(description = "Valor total de la compra")
    private double total;
    @Schema(description = "Fecha de la compra")
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Detalles del comprobante")
    private List<DetalleComprobante> detalleComprobantes = new ArrayList<>();

    @PrePersist
    @Schema(description = "Fecha en la que se creo el comprobante")
    public void prePersist(){
        this.fecha = LocalDateTime.now();
    }

}
