package org.glsid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class BourseGatewayRestService {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/names")
    public Collection<Societe> getSocietes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
      ParameterizedTypeReference<Resources<Societe>> responseType=new ParameterizedTypeReference<Resources<Societe>>() {
    };
      return restTemplate.exchange("http://localhost:9999/societe-service/societes", HttpMethod.GET, null,responseType).getBody().getContent();
    }
}


class Societe{
    private Long id;
    private String nomSociete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }
}
