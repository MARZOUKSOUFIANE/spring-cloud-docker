package com.mcommandes.service;


import com.mcommandes.dto.ProductDto;
import com.mcommandes.dto.CommandeDto;
import com.mcommandes.exceptions.ImpossibleAjouterCommandeException;
import com.mcommandes.mapper.CommandeMapper;
import com.mcommandes.model.Commande;
import com.mcommandes.proxies.ProductsProxy;
import com.mcommandes.repository.CommandeRepository;
import com.mcommandes.exceptions.CommandeNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProductsProxy productsProxy;

    public List<CommandeDto> getCommandes(){
        List<Commande> commandes = (List<Commande>) commandeRepository.findAll();
        return CollectionUtils.emptyIfNull(commandes)
                .stream()
                .map(CommandeMapper::map)
                .collect(Collectors.toList());
    }

    public CommandeDto getCommandeById(int id){
        Optional<Commande> commande = commandeRepository.findById(id);
        if(commande.isPresent()){
            return CommandeMapper.map(commande.get());
        }else{
           throw  new CommandeNotFoundException("La commande correspondante à l'id " + id + " n'existe pas");
        }
    }

    public ProductDto getCommandeProduct(int id){
        CommandeDto commande = getCommandeById(id);
        return productsProxy.getProductById(commande.productId);
    }

    public CommandeDto saveCommande(@RequestBody Commande commande){
        ProductDto produit=productsProxy.getProductById(commande.getProductId());
        Double total=commande.getQuantite()*produit.getPrix();
        commande.setTotal(total);
        Optional<Commande> nouvelleCommande=Optional.of(commandeRepository.save(commande));
        if(nouvelleCommande.isPresent()){
            return CommandeMapper.map(nouvelleCommande.get());
        }else{
            throw  new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");
        }
    }
}
