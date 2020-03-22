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
                .route(r -> r.path("/produits/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/produitsFallback")))
                        .uri("lb://PRODUIT-SERVICE").id("r1"))

                .route(r -> r.path("/commandes/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/commandesFallback")))
                        .uri("lb://COMMANDE-SERVICE").id("r2"))

                .route(r -> r.path("/paiement/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/paiementFallback")))
                        .uri("lb://PAIEMENT-SERVICE").id("r3"))

                .build();
    }
}


