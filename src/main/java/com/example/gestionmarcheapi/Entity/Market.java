package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

   @OneToOne
   @JoinColumn(name = "idProject")
   @JsonIgnore
   private Project project;
}
