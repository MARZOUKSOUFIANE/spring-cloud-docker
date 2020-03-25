package com.mpaiement.repository;

import com.mpaiement.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Integer>{

    Paiement findByidCommande(int idCommande);
}
