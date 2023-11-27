package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarket;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User utilisateur;

    //private String statut;

    @OneToOne
    private Project project;

}
