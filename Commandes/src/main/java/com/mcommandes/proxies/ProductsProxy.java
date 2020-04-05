package com.mcommandes.proxies;

import com.mcommandes.dto.ProductDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "produit-service")
@RibbonClient(name = "produit-service")
public interface ProductsProxy {

    @GetMapping(value = "/produits")
    List<ProductDto> getProducts();

    @GetMapping( value = "/produits/{id}")
    ProductDto getProductById(@PathVariable("id") int id);
}