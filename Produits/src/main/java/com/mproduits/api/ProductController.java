package com.mproduits.api;

import com.mproduits.dto.ProductDto;
import com.mproduits.model.Product;
import com.mproduits.repository.ProductRepository;
import com.mproduits.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productDao;

    @Autowired
    private ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping(value = "/produits")
    public List<ProductDto> getProducts(){
        logger.info("consultation de la liste des produits");
        return productService.getProducts();
    }

    @GetMapping( value = "/produits/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        logger.info("recuperer produit id {}",id);
        return productService.getProductById(id);
    }

}

