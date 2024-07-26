package com.example.ecommercefinal.ecommerce.controllers;

import com.example.ecommercefinal.ecommerce.entities.Carrito;
import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.entities.Comprobante;
import com.example.ecommercefinal.ecommerce.entities.DetalleComprobante;
import com.example.ecommercefinal.ecommerce.services.CarritoService;
import com.example.ecommercefinal.ecommerce.services.ClienteService;
import com.example.ecommercefinal.ecommerce.services.ComprobanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comprobantes")
@Tag(name= "Voucher Routes", description = "CRUD para manejar las funciones asociadas a los comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener comprobantes", description = "Obtiene una lista que contiene todos los comprobantes almacenados en la base de datos")
    public List<Comprobante> getAllComprobantes(){
        return comprobanteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener comprobante por ID", description = "Permite obtener un comprobante en especifico siempre y cuando se proporcione el Id correspondiente")
    public ResponseEntity<Comprobante> getComprobanteById(@PathVariable Long id){
        Optional<Comprobante> comprobante = comprobanteService.findById(id);
        return comprobante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/latest/{clid}")
    @Operation(summary = "Obtener ultimo comprobante", description = "Permite obtener el comprobante correspondiente a la ultima compra realizada por el cliente")
    public ResponseEntity<Comprobante> getLatestComprobante(@PathVariable Long clid){
        Optional<Cliente> cliente = clienteService.findById(clid);
        if (cliente.isPresent()){
            Optional<Comprobante> latestComprobante = comprobanteService.findLatestByCliente(cliente.get());
            return latestComprobante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear comproabante", description = "Permite generar un comprobante asociado a la compra realizada por el cliente")
    public ResponseEntity<Comprobante> generateInvoice(@RequestParam Long clid){
        Optional<Cliente> cliente = clienteService.findById(clid);

        if (cliente.isPresent()){
            List<Carrito> carritos = carritoService.findByClienteAndDelivered(cliente.get(), false);

            if (carritos.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Comprobante comprobante = new Comprobante();
            comprobante.setCliente(cliente.get());

            double total = 0;

            for (Carrito carrito : carritos){
                DetalleComprobante detalle = new DetalleComprobante();
                detalle.setProducto(carrito.getProducto());
                detalle.setCantidad(carrito.getCantidad());
                detalle.setPrecio(carrito.getProducto().getPrecio());
                detalle.setComprobante(comprobante);
                total += carrito.getProducto().getPrecio() * carrito.getCantidad();
                carrito.setDelivered(true);
                carritoService.save(carrito);
                comprobante.getDetalleComprobantes().add(detalle);
            }

            comprobante.setTotal(total);

            Comprobante savedComprobante = comprobanteService.save(comprobante);
            return ResponseEntity.ok(savedComprobante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar el comprobante", description = "Permite modificar el comprobante obtenido a base de la compra realizada")
    public ResponseEntity<Comprobante> updateComprobante(@PathVariable Long id, @RequestBody Comprobante comprobante){
        if (comprobanteService.findById(id).isPresent()){
            comprobante.setId(id);
            Comprobante updatedComprobante = comprobanteService.save(comprobante);
            return ResponseEntity.ok(updatedComprobante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar comprobante", description = "Permite borrar un comprobante en especifico pasandole el Id correcto")
    public ResponseEntity<Void> deleteComprobante(@PathVariable Long id){
        if (comprobanteService.findById(id).isPresent()){
            comprobanteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
