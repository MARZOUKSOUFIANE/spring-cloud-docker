package com.mcommandes.web.controller;


import com.mcommandes.beans.ProductBean;
import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.proxies.MicroserviceProduitsProxy;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;
    @Autowired
    MicroserviceProduitsProxy produitsProxy;

    /*@PostMapping (value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }*/

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }

    @GetMapping(value = "/commandes/{id}/produits")
    public ProductBean getProduitsCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return produitsProxy.recupererUnProduit(commande.get().getProductId());
    }

    @GetMapping(value = "/commandes")
    public List<Commande> getCommandes(){
        List<Commande> commandes = commandesDao.findAll();

        if(commandes.isEmpty()) throw new CommandeNotFoundException("Aucune commande n'est disponible");

        return commandes;
    }

    @PostMapping(value = "/commandes")
    public Commande passerCommande(@RequestBody Commande commande){
        ProductBean produit=produitsProxy.recupererUnProduit(commande.getProductId());
        Double total=commande.getQuantite()*produit.getPrix();
        commande.setTotal(total);
        return commandesDao.save(commande);
    }

    @PatchMapping(value = "/commandes")
    public void patchCommande(@RequestBody Commande commande){

        commandesDao.save(commande);
    }

}
