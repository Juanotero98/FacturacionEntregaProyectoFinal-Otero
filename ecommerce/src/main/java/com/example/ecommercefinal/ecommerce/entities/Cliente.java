package com.example.ecommercefinal.ecommerce.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico del cliente", example = "2")
    private Long id;
    @Schema(description = "Nombre del cliente", example = "Gonzalo")
    private String nombre;
    @Schema(description = "Apellido del cliente", example = "Perez")
    private String apellido;
    @Schema(description = "Documento del cliente", example = "123456")
    private String documento;
    @Schema(description = "Email del cliente", example = "Gonzalo@Coder.com")
    private String email;
}
