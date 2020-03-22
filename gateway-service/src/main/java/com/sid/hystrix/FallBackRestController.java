package com.sid.hystrix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallBackRestController{

@GetMapping("/produitsFallback")
   public Map <String,String > produitsFallback() {
        Map<String, String> map = new HashMap<>();
        map.put("produit1", "Table");
        map.put("produit2", "Chaise");
        map.put("produit3", "Bougie");
        map.put("produit4", "Horloge");
        map.put("message", "Default Rest produits Fallback service");

    return map;
}


@GetMapping("/commandesFallback")
    public Map <String, String>commandesFallback(){
    Map<String, String> map = new HashMap<>();
    map.put("commande1", "Commande sur le produit Table");
    map.put("commande2", "Commande sur le produit Bougie");
    map.put("commande3", "Commande sur le produit Horloge");
    map.put("message", "Default commande Fallback service");

    return map;
    }

@GetMapping("/paiementFallback")
    public Map <String, String> paiementFallback(){
    Map<String, String> map = new HashMap<>();
    map.put("paiement1", "Paiement de la commande N1");
    map.put("paiement2", "Paiement de la commande N2");
    map.put("paiement3", "Paiement de la commande N3");
    map.put("message", "Default paiement Fallback service");

    return map;
    }


    }