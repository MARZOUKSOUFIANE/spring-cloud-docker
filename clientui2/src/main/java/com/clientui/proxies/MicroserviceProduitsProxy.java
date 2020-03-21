package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "gateway-service")
@RibbonClient(name = "PRODUIT-SERVICE")
public interface MicroserviceProduitsProxy {

    @GetMapping(value = "/PRODUIT-SERVICE/produits")
    List<ProductBean> listeDesProduits();

    @GetMapping( value = "/PRODUIT-SERVICEe/produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);
}