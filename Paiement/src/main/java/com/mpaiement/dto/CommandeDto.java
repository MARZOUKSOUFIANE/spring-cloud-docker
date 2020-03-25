package com.mpaiement.dto;

import java.util.Date;

public class CommandeDto {

    public Integer productId;

    public Date dateCommande;

    public Integer quantite;

    public Boolean commandePayee;

    public Double total;

    public CommandeDto(Integer productId, Date dateCommande, Integer quantite, Boolean commandePayee, Double total) {
        this.productId = productId;
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.commandePayee = commandePayee;
        this.total = total;
    }

    public CommandeDto() {
    }
}
