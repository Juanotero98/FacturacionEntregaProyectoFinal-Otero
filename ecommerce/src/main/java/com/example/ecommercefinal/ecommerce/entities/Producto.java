package com.example.ecommercefinal.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico del producto")
    private Long id;
    @Schema(description = "Nombre del producto")
    private String nombre;
    @Schema(description = "Descripcion del prodcuto")
    private String descripcion;
    @Schema(description = "cantidad en stock de dicho producto")
    private int stock;
    @Schema(description = "Precio del producto")
    private double precio;
}
