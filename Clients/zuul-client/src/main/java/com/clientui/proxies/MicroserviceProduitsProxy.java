package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "proxy-service")
@RibbonClient(name = "produit-service")
public interface MicroserviceProduitsProxy {

    @GetMapping(value = "/produit-service/produits")
    List<ProductBean> listeDesProduits();

    @GetMapping( value = "/produit-service/produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);
}