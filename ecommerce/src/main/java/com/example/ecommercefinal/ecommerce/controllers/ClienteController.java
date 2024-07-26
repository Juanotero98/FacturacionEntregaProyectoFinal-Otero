package com.example.ecommercefinal.ecommerce.controllers;

import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Clients Routes", description = "Conjunto de funciones CRUD para manejar las rutas asociadas a los clientes ")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Obtiene una lista con todos los clientes almacenados en la base de datos")
    public List<Cliente> getAllClientes(){
        return clienteService.findAll();
    }

    @GetMapping("/{cid}")
    @Operation(summary = "Obtiene un cliente especifico por id", description = "se le pasa un Id para localizar a un cliente en especifico")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("cid") Long cid){
        Optional<Cliente> cliente = clienteService.findById(cid);
        return cliente.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    @Operation(summary = "Crear un cliente", description = "Permite la creacion de un cliente para realizar las compras correspondientes")
    public Cliente registerCliente(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{cid}")
    @Operation(summary = "Modifcar datos del cliente", description = "Pasandole un Id en especifico esta funcion permite modificar datos de un determinado cliente")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("cid") Long cid, @RequestBody Cliente cliente){
        if (clienteService.findById(cid).isPresent()){
            cliente.setId(cid);
            return ResponseEntity.ok(clienteService.save(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cid}")
    @Operation(summary = "Eliminar cliente", description = "Permite eliminar un cliente de la base de datos, se le debe pasar el Id correspondiente a ese cliente")
    public ResponseEntity<Void> deleteCliente(@PathVariable("cid") Long cid){
        if (clienteService.findById(cid).isPresent()){
            clienteService.deleteById(cid);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
