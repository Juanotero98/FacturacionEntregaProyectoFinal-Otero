package com.example.ecommercefinal.ecommerce.repositories;

import com.example.ecommercefinal.ecommerce.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
