package com.example.gestiondesformations.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto {
    private String status;

    private String message;
}
