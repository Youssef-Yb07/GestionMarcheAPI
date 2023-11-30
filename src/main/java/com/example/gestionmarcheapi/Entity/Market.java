package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
   @JsonManagedReference
   private Project project;
}
