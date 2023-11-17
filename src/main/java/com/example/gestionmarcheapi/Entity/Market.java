package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;

import java.util.List;

public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;
    private String statut;
    @OneToMany(mappedBy = "marche", cascade = CascadeType.ALL)
    private List<Project> projets;
}
