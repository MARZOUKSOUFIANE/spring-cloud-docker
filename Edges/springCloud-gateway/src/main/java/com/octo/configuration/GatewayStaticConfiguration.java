package com.octo.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayStaticConfiguration {

   /* @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(r -> r.path("/produits/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/produitsFallback")))
                        .uri("http://produits:9001/").id("r1"))

                .route(r -> r.path("/commandes/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/commandesFallback")))
                        .uri("http://commandes:9002/").id("r2"))

                .route(r -> r.path("/paiements/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/paiementFallback")))
                        .uri("http://paiement:9003/").id("r3"))

                .build();
    }*/
}
