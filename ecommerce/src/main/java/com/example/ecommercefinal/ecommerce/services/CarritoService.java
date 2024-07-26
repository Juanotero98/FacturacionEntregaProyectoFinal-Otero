package com.example.ecommercefinal.ecommerce.services;


import com.example.ecommercefinal.ecommerce.entities.Carrito;
import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll(){
        return carritoRepository.findAll();
    }

    public Optional<Carrito> findById(Long id){
        return carritoRepository.findById(id);
    }

    public List<Carrito> findByClienteAndDelivered(Cliente cliente, boolean delivered){
        return carritoRepository.findByClienteAndDelivered(cliente, delivered);
    }

    public Carrito save(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public void deleteById(Long id){
        carritoRepository.deleteById(id);
    }
}
