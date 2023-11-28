package com.example.gestionmarcheapi.Entity;

import com.example.gestionmarcheapi.Entity.Enumerations.EtatCommentaire;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommentaire;

    private String contenu;

    @Enumerated(EnumType.STRING)
    private EtatCommentaire etat;

    @ManyToOne
    @JoinColumn(name = "idTask")
    private Tache task;

    @OneToOne
    private User employee;


}
