package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "proxy-service",url = "http://localhost:9004")
@RibbonClient(name = "produit-service")
public interface MicroserviceProduitsProxy {

    @GetMapping(value = "/produit-service/Produits")
    List<ProductBean> listeDesProduits();

    @GetMapping( value = "/produit-service/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);
}