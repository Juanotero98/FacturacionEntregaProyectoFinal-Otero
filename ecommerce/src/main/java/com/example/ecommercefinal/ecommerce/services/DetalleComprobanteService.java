package com.example.ecommercefinal.ecommerce.services;

import com.example.ecommercefinal.ecommerce.entities.DetalleComprobante;
import com.example.ecommercefinal.ecommerce.repositories.DetalleComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleComprobanteService {
    @Autowired
    private DetalleComprobanteRepository detalleComprobanteRepository;

    public List<DetalleComprobante> findAll(){
        return detalleComprobanteRepository.findAll();
    }

    public Optional<DetalleComprobante> findById(Long id){
        return detalleComprobanteRepository.findById(id);
    }

    public DetalleComprobante save(DetalleComprobante detalleComprobante){
        return detalleComprobanteRepository.save(detalleComprobante);
    }

    public void deletedById(Long id){
        detalleComprobanteRepository.deleteById(id);
    }
}
