package com.example.gestiondesformations.dto;


import com.example.gestiondesformations.entity.Formation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdherentDto {
    private String firstName;
    private String lastName;
    private String email;
}
