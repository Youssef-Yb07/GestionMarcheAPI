package com.example.gestionmarcheapi.Entity.DTO;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.example.gestionmarcheapi.Entity.Market;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Date;

@Data
public class ProjectDTO {

    private Integer idProject;

    private String nom;

    private String description;

    private Date dateDebut;
    
    private Date dateFin;

    private Double budget;

    private Market market;

    @Enumerated(EnumType.STRING)
    private StatusProject statusProject;
}
