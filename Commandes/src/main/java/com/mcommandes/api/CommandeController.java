package com.mcommandes.api;

import com.mcommandes.dto.ProductDto;
import com.mcommandes.dto.CommandeDto;
import com.mcommandes.model.Commande;
import com.mcommandes.service.CommandeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    private final Logger logger = LoggerFactory.getLogger(CommandeController.class);


    @GetMapping(value = "/commandes")
    public List<CommandeDto> getCommandes(){
        logger.info("consultation de la liste des commande");
        return commandeService.getCommandes();
    }

    @GetMapping(value = "/commandes/{id}")
    public CommandeDto getCommandeById(@PathVariable int id){
        logger.info("recuperer la commande de l'ID :{}",id);
        return commandeService.getCommandeById(id);
    }

    @GetMapping(value = "/commandes/{id}/product")
    public ProductDto getCommandeProduct(@PathVariable int id){
        logger.info("recuperer produit de la commande de l'ID :{}",id);
        return commandeService.getCommandeProduct(id);
    }

    @PostMapping (value = "/commandes")
    public CommandeDto saveCommande(@RequestBody Commande commande){
        CommandeDto nouvelleCommande=commandeService.saveCommande(commande);
        logger.info("ajouter une commande pour le produit de l'ID :{}",nouvelleCommande.productId);
        return nouvelleCommande;
    }

    @PatchMapping(value = "/commandes")
    public CommandeDto patchCommande(@RequestBody Commande commande){
        CommandeDto CommandeModifie=commandeService.saveCommande(commande);
        logger.info("modification d'une commande pour le produit de l'ID :{}",CommandeModifie.productId);
        return CommandeModifie;
    }

}
