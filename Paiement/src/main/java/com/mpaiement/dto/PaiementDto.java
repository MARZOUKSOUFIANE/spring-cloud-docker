package com.mpaiement.dto;


public class PaiementDto {

    public Integer idCommande;

    public Integer montant;

    public Long numeroCarte;

    public PaiementDto() {
    }

    public PaiementDto(Integer idCommande, Integer montant, Long numeroCarte) {
        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
    }

}