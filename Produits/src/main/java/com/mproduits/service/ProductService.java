package com.mproduits.service;


import com.mproduits.dto.ProductDto;
import com.mproduits.exceptions.ProductNotFoundException;
import com.mproduits.mapper.ProductMapper;
import com.mproduits.model.Product;
import com.mproduits.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getProducts(){
        List<Product> products = (List<Product>) productRepository.findAll();
        return CollectionUtils.emptyIfNull(products)
                .stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return ProductMapper.map(product.get());
        }else{
           throw  new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");
        }
    }
}
