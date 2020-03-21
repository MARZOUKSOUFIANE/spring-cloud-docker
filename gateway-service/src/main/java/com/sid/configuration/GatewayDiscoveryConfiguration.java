package com.sid.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayDiscoveryConfiguration {

    @Bean
    RouteLocator gatewayRoutesDiscovery(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(r -> r.path("/produits/**").uri("lb://PRODUIT-SERVICE").id("r1"))
                .route(r -> r.path("/commandes/**").uri("lb://COMMANDE-SERVICE").id("r2"))
                .route(r -> r.path("/paiement/**").uri("lb://PAIEMENT-SERVICE").id("r3"))
                .build();
    }
}


