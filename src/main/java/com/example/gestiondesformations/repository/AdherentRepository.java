package com.example.gestiondesformations.repository;

import com.example.gestiondesformations.entity.Adherent;
import com.example.gestiondesformations.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdherentRepository extends JpaRepository<Adherent,Integer> {
    List<Adherent> getAdherentsByFormation(Formation formation);
}
