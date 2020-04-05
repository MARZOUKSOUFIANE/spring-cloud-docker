package com.mpaiement.api;

import com.mpaiement.dto.CommandeDto;
import com.mpaiement.dto.PaiementDto;
import com.mpaiement.repository.PaiementRepository;
import com.mpaiement.model.Paiement;
import com.mpaiement.service.PaiementService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class PaiementController {

    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    PaiementService paiementService;

    private final Logger logger = LoggerFactory.getLogger(PaiementController.class);


    @GetMapping(value = "/paiements")
    public List<PaiementDto> getPaiement(){
        logger.info("consultation de la liste des paiements");
        return paiementService.getPaiements();
    }

    @GetMapping(value = "/paiements/{id}")
    public PaiementDto getPaiementById(@PathVariable int id){
        logger.info("recuperer le paiment de l'ID :{}",id);
        return paiementService.getPaiementById(id);
    }


    @HystrixCommand(fallbackMethod = "paiementCommandeFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    @GetMapping(value = "/paiements/{id}/commande")
    public CommandeDto getPaiementCommande(@PathVariable int id){
        logger.info("recuperer commande du paiement de l'ID :{}",id);
        return paiementService.getPaiementCommande(id);
    }


    @PostMapping(value = "/paiements")
    public PaiementDto savePaiement(@RequestBody Paiement paiement){
        PaiementDto nouveauPaiement=paiementService.savePaiement(paiement);
        logger.info("ajouter un paiement pour la commande de l'ID :{}",nouveauPaiement.idCommande);
        return nouveauPaiement;
    }

    public CommandeDto paiementCommandeFallback(int id) {


        return new CommandeDto();
    }


}
