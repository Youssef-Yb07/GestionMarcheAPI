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
}
