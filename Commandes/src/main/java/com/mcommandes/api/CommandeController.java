package com.mcommandes.api;

import com.mcommandes.dto.ProductDto;
import com.mcommandes.dto.CommandeDto;
import com.mcommandes.model.Commande;
import com.mcommandes.service.CommandeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @HystrixCommand(fallbackMethod = "commandeProductFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
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

    @PostMapping(value = "/commandes/{id}")
    public CommandeDto patchCommande(@PathVariable int id,@RequestBody CommandeDto commande){
        CommandeDto CommandeModifie=commandeService.updateCommande(id,commande);
        logger.info("modification d'une commande pour le produit de l'ID :{}",CommandeModifie.productId);
        return CommandeModifie;
    }

    public ProductDto commandeProductFallback(int id) {

        return new ProductDto();
    }

}
