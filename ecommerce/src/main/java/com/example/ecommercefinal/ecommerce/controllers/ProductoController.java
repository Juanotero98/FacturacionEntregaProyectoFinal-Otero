package com.example.ecommercefinal.ecommerce.controllers;

import com.example.ecommercefinal.ecommerce.entities.Producto;
import com.example.ecommercefinal.ecommerce.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products Routes", description = "Conjunto de funciones CRUD para manejar las funcionalidaes asociadas a los productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Recibe todos los productos", description = "Recibe una lista con todos los productos presentes en la base de datos")
    public List<Producto> getAllProductos(){
        return productoService.findAll();
    }

    @GetMapping("/{pid}")
    @Operation(summary = "Recibe un producto por id", description = "Recibe un producto en especifico, usando el Id de dicho producto para encontrarlo")
    public ResponseEntity<Producto> getProductoById(@PathVariable("pid") Long pid){
        Optional<Producto> producto = productoService.findById(pid);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un producto", description = "Permite crear un producto y agregar este mismo a la base de datos")
    public Producto createProducto(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @PutMapping("/{pid}")
    @Operation(summary = "Modificar un producto", description = "Perimte modificar un producto en especifico, guiandose mediante el uso del Id correspondiente")
    public ResponseEntity<Producto> updateProducto(@PathVariable("pid") Long pid, @RequestBody Producto producto){
        if (productoService.findById(pid).isPresent()){
            producto.setId(pid);
            return ResponseEntity.ok(productoService.save(producto));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{pid}")
    @Operation(summary = "Eliminar Producto", description = "Permite eliminar un producto en especifico de la base de datos, se deba pasar el id indicado para encontrar dicho producto")
    public ResponseEntity<Void> deleteProducto(@PathVariable("pid") Long pid){
        if (productoService.findById(pid).isPresent()){
            productoService.deleteById(pid);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
