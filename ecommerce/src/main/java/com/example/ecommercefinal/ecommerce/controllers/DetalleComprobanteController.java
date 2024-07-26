package com.example.ecommercefinal.ecommerce.controllers;


import com.example.ecommercefinal.ecommerce.entities.DetalleComprobante;
import com.example.ecommercefinal.ecommerce.services.DetalleComprobanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalles")
@Tag(name = "Voucher Detail Routes", description = "Conjunto de funciones CRUD para manejar las funcionalidades de los detalles de los comprobantes")
public class DetalleComprobanteController {
    @Autowired
    private DetalleComprobanteService detalleComprobanteService;

    @GetMapping
    @Operation(summary = "Obtener detalles", description = "Obtiene una lista con todos los detalles relacionasdos a la compra hecha por el cliente")
    public List<DetalleComprobante> getAllDetalles(){
        return detalleComprobanteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene detalles por Id", description = "Obtiene detalles especificos dependiendo del Id pasado.")
    public ResponseEntity<DetalleComprobante> getDtalleById(@PathVariable Long id){
        Optional<DetalleComprobante> detalle = detalleComprobanteService.findById(id);
        return detalle.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Agregar detalles", description = "Permite agregar detalles al  comprobante de la compra realizada por el cliente")
    public DetalleComprobante createDetalle(@RequestBody DetalleComprobante detalleComprobante){
        return detalleComprobanteService.save(detalleComprobante);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar Comprobante", description = "Permite modificar detalles del comprobante de compra, se debe pasar id correspondiete para poder encontrar el comprobante")
    public ResponseEntity<DetalleComprobante> updateDetalle(@PathVariable Long id, @RequestBody DetalleComprobante detalleComprobante){
        if (detalleComprobanteService.findById(id).isPresent()){
            detalleComprobante.setId(id);
            return ResponseEntity.ok(detalleComprobanteService.save(detalleComprobante));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar detalles", description = "Permite borrar detalles del comprobante de compra del cliente")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id){
        if (detalleComprobanteService.findById(id).isPresent()){
            detalleComprobanteService.deletedById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
