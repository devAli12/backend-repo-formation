package com.example.gestiondesformations.repository;

import com.example.gestiondesformations.entity.Formation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Integer> {
    @Query("SELECT f FROM Formation f WHERE f.titre LIKE %:text%")
    List<Formation> findFormationsByNameContaining(String text);
}
