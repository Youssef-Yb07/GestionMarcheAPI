package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idService;

    @Column
    private String libelle;

    @OneToMany(mappedBy = "service")
    private List<User> employees;

    @OneToOne
    @JoinColumn(name = "idChefService")
    private User chefService;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;
}
