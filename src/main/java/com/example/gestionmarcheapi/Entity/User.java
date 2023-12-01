package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "idEmployee"),
            inverseJoinColumns = @JoinColumn(name = "idProject"))
    @JsonIgnore
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;

    @OneToOne
    @JoinColumn(name = "idCommentaire")
    private Commentaire commentaire;


}
