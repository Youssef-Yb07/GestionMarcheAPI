package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String nom;

    private String prenom;

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name ="idRole")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;

    @OneToOne
    @JoinColumn(name = "idCommentaire")
    private Commentaire commentaire;


}
