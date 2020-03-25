package com.mpaiement.service;


import com.mpaiement.dto.CommandeDto;
import com.mpaiement.dto.PaiementDto;
import com.mpaiement.exceptions.PaiementExistantException;
import com.mpaiement.exceptions.PaiementImpossibleException;
import com.mpaiement.exceptions.PaiementNotFoundException;
import com.mpaiement.mapper.PaiementMapper;
import com.mpaiement.model.Paiement;
import com.mpaiement.proxies.CommandeProxy;
import com.mpaiement.repository.PaiementRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private CommandeProxy commandeProxy;

    public List<PaiementDto> getPaiements(){
        List<Paiement> paiements = (List<Paiement>) paiementRepository.findAll();
        return CollectionUtils.emptyIfNull(paiements)
                .stream()
                .map(PaiementMapper::map)
                .collect(Collectors.toList());
    }

    public PaiementDto getPaiementById(int id){
        Optional<Paiement> paiement= paiementRepository.findById(id);
        if(paiement.isPresent()){
            return PaiementMapper.map(paiement.get());
        }else{
            throw  new PaiementNotFoundException("Le paiement correspondante à l'id " + id + " n'existe pas");
        }
    }

    public CommandeDto getPaiementCommande(int id){
        PaiementDto paiement = getPaiementById(id);
        return commandeProxy.getCommandeById(paiement.idCommande);
    }

    public PaiementDto savePaiement(Paiement paiement){
        Paiement paiementExistant=paiementRepository.findByidCommande(paiement.getIdCommande());
        if(paiementExistant == null) {
            Paiement nouveauPaiement = paiementRepository.save(paiement);
            if(nouveauPaiement == null) throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");
            else {
                CommandeDto commande=getPaiementCommande(nouveauPaiement.getId());
                //marquer la commande payée
                commande.commandePayee=true;
                commandeProxy.updateCommande(nouveauPaiement.getIdCommande(),commande);
                return PaiementMapper.map(nouveauPaiement);
            }
        }
        else{
            throw new PaiementExistantException("ce paiement existe deja ");
        }
    }
}
