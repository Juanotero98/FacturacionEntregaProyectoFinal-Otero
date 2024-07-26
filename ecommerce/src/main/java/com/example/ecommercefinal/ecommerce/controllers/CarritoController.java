package com.example.ecommercefinal.ecommerce.controllers;

import com.example.ecommercefinal.ecommerce.entities.Carrito;
import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.entities.Producto;
import com.example.ecommercefinal.ecommerce.services.CarritoService;
import com.example.ecommercefinal.ecommerce.services.ClienteService;
import com.example.ecommercefinal.ecommerce.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name = "Carts Routes", description = "estas rutas contienen las funciones de agregar, quitar o modificar productos del carrito de compra del cliente")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/{clid}/{pid}/{q}")
    @Operation(summary = "Crear carrito de compra", description = "Permite crear un carrito para realizar la compra correspondiente, se le debe pasar id del cliente, id del producto y la cantidad a comprar")
    public ResponseEntity<Carrito> addProductToCart(@PathVariable Long clid, @PathVariable Long pid, @PathVariable int q){
        Optional<Cliente> cliente = clienteService.findById(clid);
        Optional<Producto> producto = productoService.findById(pid);

        if (cliente.isPresent() && producto.isPresent()){
            Carrito carrito = new Carrito();
            carrito.setCliente(cliente.get());
            carrito.setProducto(producto.get());
            carrito.setCantidad(q);
            carrito.setDelivered(false);
            return ResponseEntity.ok(carritoService.save(carrito));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cid}")
    @Operation(summary = "Eliminar carrito", description = "Permite eliminar el carrito de compra")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cid){
        Optional<Carrito> carrito = carritoService.findById(cid);
        if (carrito.isPresent()){
            carritoService.deleteById(cid);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clid}")
    @Operation(summary = "Obtener carrito por Id", description = "Permite obtener un carrito especifico de todos los que se encuentran en la base de datos, se le debe pasar el Id correspondiente para ecnontrar este")
    public ResponseEntity<List<Carrito>> getProductsInCart(@PathVariable Long clid){
        Optional<Cliente> cliente = clienteService.findById(clid);
        if (cliente.isPresent()){
            List<Carrito> carritos= carritoService.findByClienteAndDelivered(cliente.get(), false);
            return ResponseEntity.ok(carritos);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
