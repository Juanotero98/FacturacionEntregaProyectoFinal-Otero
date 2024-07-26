package com.example.ecommercefinal.ecommerce.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Facturacion Entrega Proyecto Final Otero API",
                version = "1.0",
                description = "Eccomerce que contiene un CRUD de Clientes, productos, carritos, comprobantes y los detalles de estos ultimos "
        )
)
public class OpenApi {

}
