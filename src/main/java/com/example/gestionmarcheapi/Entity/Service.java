package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @JsonManagedReference
    private List<User> employees;

    @OneToOne
    @JoinColumn(name = "idChefService")
    private User chefService;

}
