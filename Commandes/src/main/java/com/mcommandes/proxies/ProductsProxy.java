package com.mcommandes.proxies;

import com.mcommandes.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "produit-service",url = "http://localhost:9001/")
public interface ProductsProxy {

    @GetMapping(value = "/produits")
    List<ProductDto> getProducts();

    @GetMapping( value = "/produits/{id}")
    ProductDto getProductById(@PathVariable("id") int id);
}