package com.mpaiement.mapper;

import com.mpaiement.dto.PaiementDto;
import com.mpaiement.model.Paiement;


public interface PaiementMapper {

    static PaiementDto map(Paiement paiement){
        return new PaiementDto(paiement.getIdCommande(),paiement.getMontant(),paiement.getNumeroCarte());
    }
}
