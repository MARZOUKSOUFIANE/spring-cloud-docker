package com.mcommandes.mapper;

import com.mcommandes.dto.CommandeDto;
import com.mcommandes.model.Commande;


public interface CommandeMapper {

    static CommandeDto map(Commande commande){
        return new CommandeDto(commande.getProductId(),commande.getDateCommande(),commande.getQuantite(),commande.getCommandePayee(),commande.getTotal());
    }
}
