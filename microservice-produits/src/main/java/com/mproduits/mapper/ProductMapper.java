package com.mproduits.mapper;

import com.mproduits.dto.ProductDto;
import com.mproduits.model.Product;

public interface ProductMapper {

    static ProductDto map(Product product){
        return new ProductDto(product.getTitre(),product.getDescription(),product.getImage(),product.getPrix());
    }
}
