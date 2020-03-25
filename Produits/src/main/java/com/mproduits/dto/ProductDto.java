package com.mproduits.dto;

public class ProductDto {
    public String titre;

    public String description;

    public String image;

    public Double prix;

    public ProductDto(String titre, String description, String image, Double prix) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public ProductDto() {
    }
}
