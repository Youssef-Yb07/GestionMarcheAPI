package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProject;

    @OneToMany(mappedBy = "project")
    private List<Tache> tasks;

    @ManyToOne
    @JoinColumn(name = "idMarket")
    private Market market;

}
