package com.example.gestiondesformations.controller;


import com.example.gestiondesformations.dto.AdherentDto;
import com.example.gestiondesformations.dto.ResponseDto;
import com.example.gestiondesformations.entity.Adherent;
import com.example.gestiondesformations.entity.Formation;
import com.example.gestiondesformations.repository.AdherentRepository;
import com.example.gestiondesformations.repository.FormationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/adherents")
public class AdherentController {
    private AdherentRepository adherentRepository;

    private FormationRepository formationRepository;


    @GetMapping
    public ResponseEntity<List<Adherent>> getAllAdherents(){
        List<Adherent> adherents = adherentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(adherents);
    }

    @GetMapping("/formation")
    public ResponseEntity<List<Adherent>> getAdherentsByFormation(@RequestParam("id") int formationId){
        Formation formation = formationRepository.findById(formationId).orElseThrow(()->{
            throw new RuntimeException("this formation doesn't have any adhrents");
        });
        List<Adherent> adherents = adherentRepository.getAdherentsByFormation(formation);
        return ResponseEntity.status(HttpStatus.OK).body(adherents);
    }

    @PostMapping
    public  ResponseEntity<ResponseDto> createAdherent(@RequestBody AdherentDto adherentDto,@RequestParam("formationId") int id){
        Adherent adherent = new Adherent();
        adherent.setFirstName(adherentDto.getFirstName());
        adherent.setLastName(adherentDto.getLastName());
        adherent.setEmail(adherentDto.getEmail());
        Formation formation= formationRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("formation with this id doesn't exist");
        });
        formation.getAdherents().add(adherent);
        adherent.setFormation(formation);
        formationRepository.save(formation);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201","adherent is added to formation"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAdherent(@ PathVariable int id){
        adherentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","adherent deleted from formation"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateAdherent(@RequestBody AdherentDto adherentDto, @PathVariable  int id){
        Adherent adherent=adherentRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("adherent with this id doesn't exist");
        });
        adherent.setFirstName(adherentDto.getFirstName());
        adherent.setLastName(adherentDto.getLastName());
        adherent.setEmail(adherentDto.getEmail());
        adherentRepository.save(adherent);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","adhrent was updated success"));
    }

}