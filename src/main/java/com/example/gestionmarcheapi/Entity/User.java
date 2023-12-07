package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @JsonBackReference
    private Service service;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idCommentaire")
    private Commentaire commentaire;


}
