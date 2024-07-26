package com.example.ecommercefinal.ecommerce.repositories;

import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.entities.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    Optional<Comprobante> findTopByClienteOrderByFechaDesc(Cliente cliente);
}
