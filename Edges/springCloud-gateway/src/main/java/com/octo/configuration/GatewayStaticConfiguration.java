package com.octo.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayStaticConfiguration {

   /* @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
       return builder.routes()
                .route(r -> r.path("/produits/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/produitsFallback")))
                        .uri("http://localhost:9001/").id("r1"))

                .route(r -> r.path("/commandes/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/commandesFallback")))
                        .uri("http://localhost:9002/").id("r2"))

                .route(r -> r.path("/paiements/**").filters (f ->
                        f.hystrix(h->h.setName("Hystrix").setFallbackUri("forward:/paiementFallback")))
                        .uri("http://localhost:9003/").id("r3"))

                .build();
    }*/
}
