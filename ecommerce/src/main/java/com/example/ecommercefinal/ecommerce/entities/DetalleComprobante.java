package com.example.ecommercefinal.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;

@Entity
@Data
@ToString
@Table(name = "detalles comprobante")
public class DetalleComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id unico del comrpobante")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comprobante_id")
    @JsonBackReference
    @Schema(description = "Datos del comporbante")
    private Comprobante comprobante;

    @ManyToOne
    @Schema(description = "Datos del producto")
    private Producto producto;
    @Schema(description = "Cantidad de productos comprada")
    private int cantidad;
    @Schema(description = "Precio del producto")
    private double precio;
}
