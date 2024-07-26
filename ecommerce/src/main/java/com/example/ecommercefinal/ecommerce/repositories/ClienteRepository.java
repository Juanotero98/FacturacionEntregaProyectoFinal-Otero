package com.example.ecommercefinal.ecommerce.repositories;

import com.example.ecommercefinal.ecommerce.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
