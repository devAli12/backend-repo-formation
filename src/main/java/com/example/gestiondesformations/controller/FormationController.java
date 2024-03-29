package com.example.gestiondesformations.controller;

import com.example.gestiondesformations.dto.FormationDto;
import com.example.gestiondesformations.dto.ResponseDto;
import com.example.gestiondesformations.entity.Formation;
import com.example.gestiondesformations.repository.FormationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/formations")
public class FormationController {

    private FormationRepository formationRepository;


    @GetMapping
    public ResponseEntity<List<Formation>> getFormations(){
        List<Formation> formations = formationRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(formations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable int id){
        Formation formation = formationRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(formation);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteFormation(@PathVariable int id){
          formationRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","formation removed succusefully"));
    }

    @DeleteMapping("/delete/{idsStr}")
    public ResponseEntity<ResponseDto> deleByIds(@PathVariable String idsStr){
         String[] formationIds =idsStr.split(",");
        List<Integer> ids= new ArrayList<>();
        for(String s:formationIds) ids.add(Integer.parseInt(s));
        formationRepository.deleteAllById(ids);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","formations selected removed succusefully"));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createFormation(@RequestBody  FormationDto formationDto){
        Formation formation =Formation.builder()
                .titre(formationDto.getTitre())
                .description(formationDto.getDescription())
                .dateFin(formationDto.getDateFin())
                .dateDebut(formationDto.getDateDebut()).build();
         formationRepository.save(formation);
         return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201","formation created avec success"));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ResponseDto> updateFormation(@PathVariable int id,@RequestBody  FormationDto formationDto){
        Formation formation =Formation.builder()
                .id(id)
                .titre(formationDto.getTitre())
                .description(formationDto.getDescription())
                .dateFin(formationDto.getDateFin())
                .dateDebut(formationDto.getDateDebut()).build();
        formationRepository.save(formation);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201","formation updated avec success"));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteAll(){
        formationRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","All formations removed succusefully"));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Formation>> searchByTitre(@RequestParam("keyword") String searchText){
        List<Formation> formations = this.formationRepository.findFormationsByNameContaining(searchText);
        return  ResponseEntity.status(HttpStatus.OK).body(formations);
    }

}
