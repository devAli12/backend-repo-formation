package com.example.gestiondesformations.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    private String statusCode;
    private String errorMessage;
}
