package com.example.ecommercefinal.ecommerce.entities;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@ToString
@Table(name = "carrito")
@Schema(description = "Entidad que representa un carrito de comrpa")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico del carrito", example = "1")
    private Long id;

    @ManyToOne
    @Schema(description = "Datos del cliente")
    private Cliente cliente;

    @ManyToOne
    @Schema(description = "Datos correspondietes al producto")
    private Producto producto;

    @Schema(description = "Cantidad comprada", example = "45")
    private int cantidad;
    private boolean delivered = false;
}
