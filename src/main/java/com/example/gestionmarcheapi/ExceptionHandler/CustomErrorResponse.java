package com.example.gestionmarcheapi.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */

@Data
@AllArgsConstructor
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
