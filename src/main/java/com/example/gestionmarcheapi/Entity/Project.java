package com.example.gestionmarcheapi.Entity;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProject;

    private String nom;

    private String description;

    private Date dateDebut;

    private Date dateFin;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private StatusProject statusProject;

    @OneToOne
    @JoinColumn(name = "idMarket")
    private Market market;

    @OneToMany(mappedBy = "project")
    private List<Tache> tasks;

    //MediumBlob is a BLOB column with a maximum length of 16,777,215 (2^24 - 1) bytes.
    //Byte array is used to store the contents of the file.
    @JsonIgnore
    @Column(name = "CDC", columnDefinition = "MediumBlob")
    private byte[] CDC_file;

    @JsonIgnore
    private String fileType;
    private String fileName;


    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idEmployee"))
    private List<User> employees;

    @OneToOne
    @JoinColumn(name = "idChefService")
    private User ChefService;

    @OneToOne
    @JoinColumn(name = "directeur_id")
    private User directeur;

}
