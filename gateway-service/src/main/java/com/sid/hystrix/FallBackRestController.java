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
        map.put("message", "Default Rest produits Fallback service");
        map.put("table", "Table");
        map.put("chaise", "Chaise");
        map.put("bougie", "Bougie");
        map.put("horloge", "Horloge");
        return map;
}


@GetMapping("/commandesFallback")
    public Map <String, String>commandesFallback(){
    Map<String, String> map = new HashMap<>();
    map.put("message", "Default commande Fallback service");
    map.put("Fajr", "07:00");
    map.put("Dohr", "14:00");
    return map;
    }

@GetMapping("/paiementFallback")
    public Map <String, String> paiementFallback(){
    Map<String, String> map = new HashMap<>();
    map.put("message", "Default paiement Fallback service");
    map.put("Fajr", "07:00");
    map.put("Dohr", "14:00");
    return map;
    }


    }