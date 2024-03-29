package com.example.gestiondesformations.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder @NoArgsConstructor
@AllArgsConstructor
@Data
public class Formation {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id ;

    private String titre;

    private String description;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "formation")
    @JsonIgnore
    private List<Adherent> adherents;
}
