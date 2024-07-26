package com.example.ecommercefinal.ecommerce.services;

import com.example.ecommercefinal.ecommerce.entities.Cliente;
import com.example.ecommercefinal.ecommerce.entities.Comprobante;
import com.example.ecommercefinal.ecommerce.repositories.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {
    @Autowired
    private ComprobanteRepository comprobanteRepository;

    public List<Comprobante> findAll(){
        return comprobanteRepository.findAll();
    }

    public Optional<Comprobante> findById(Long id){
        return comprobanteRepository.findById(id);
    }

    public Comprobante save(Comprobante comprobante){
        return comprobanteRepository.save(comprobante);
    }

    public void deleteById(Long id){
        comprobanteRepository.deleteById(id);
    }

    public Optional<Comprobante> findLatestByCliente(Cliente cliente){
        return comprobanteRepository.findTopByClienteOrderByFechaDesc(cliente);
    }
}
