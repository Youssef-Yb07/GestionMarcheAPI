package com.example.gestionmarcheapi.Entity;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
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
    //jsonBackReference pour eviter la boucle infinie lors de la serialisation
    //elle se fait avec JoinColumn car c'est une relation OneToOne
    @JsonBackReference
    private Market market;

    @OneToMany(mappedBy = "project")
    //jsonManagedReference pour eviter la boucle infinie lors de la serialisation
    //elle se fait avec mappedBy car c'est une relation OneToMany
    @JsonManagedReference
    private List<Tache> tasks;

    //MediumBlob is a BLOB column with a maximum length of 16,777,215 (2^24 - 1) bytes.
    //Byte array is used to store the contents of the file.
    @JsonIgnore
    @Column(name = "CDC", columnDefinition = "MediumBlob")
    private byte[] CDC_file;

    private String fileName;

    @JsonIgnore
    private String fileType;

}
