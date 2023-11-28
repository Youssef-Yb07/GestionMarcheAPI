package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarket;

    private String nom;

    private String description;

   @ManyToOne
   @JoinColumn(name = "idEntreprise")
   private Entreprise entreprise;

   @OneToOne
   @JoinColumn(name = "idProject")
   private Project project;
}
