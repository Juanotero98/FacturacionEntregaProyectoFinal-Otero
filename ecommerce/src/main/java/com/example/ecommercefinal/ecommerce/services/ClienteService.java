package com.example.ecommercefinal.ecommerce.services;

import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id){
        Assert.notNull(id, "El id otorgado no puede ser null");
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id){
        Assert.notNull(id, "El id otorgado no puede ser null");
        clienteRepository.deleteById(id);
    }
}
