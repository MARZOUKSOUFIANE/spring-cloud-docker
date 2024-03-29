package com.mpaiement.proxies;

import com.mpaiement.dto.CommandeDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "commande-service")
@RibbonClient(name = "commande-service")
public interface CommandeProxy {

    @GetMapping(value = "/commandes")
    List<CommandeDto> getCommandes();

    @GetMapping( value = "/commandes/{id}")
    CommandeDto getCommandeById(@PathVariable("id") int id);

    @PostMapping(value = "/commandes/{id}", consumes = "application/json")
    public CommandeDto updateCommande(@PathVariable int id,@RequestBody CommandeDto commande);
}