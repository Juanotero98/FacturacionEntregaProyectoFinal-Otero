package com.example.ecommercefinal.ecommerce.repositories;

import com.example.ecommercefinal.ecommerce.entities.Carrito;
import com.example.ecommercefinal.ecommerce.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByClienteAndDelivered(Cliente cliente, boolean delivered);
}
