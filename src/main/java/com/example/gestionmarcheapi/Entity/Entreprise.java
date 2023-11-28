package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntreprise;

    private String nom;

    private String adresse;

    @OneToMany(mappedBy = "entreprise")
    private List<Service> services;

    @OneToMany(mappedBy = "entreprise")
    private List<User> employees;

    @OneToMany(mappedBy = "entreprise")
    private List<User> ChefService;

    @OneToOne
    @JoinColumn(name = "directeur_id")
    private User directeur;

    @OneToMany(mappedBy = "entreprise")
    private List<Market> markets;
}
