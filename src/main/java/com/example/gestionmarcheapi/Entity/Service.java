package com.example.gestionmarcheapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */

@Entity
@Data
@Table
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idService;

    @Column
    private String libelle;

    @OneToMany
    @JoinTable(name="Service_user_association",
            joinColumns={@JoinColumn(name="idService")},
            inverseJoinColumns={@JoinColumn(name="idUser")})
    private List<User> usersList;

    @OneToMany
    @JoinTable(name="Service_marche_association",
            joinColumns={@JoinColumn(name="idService")},
            inverseJoinColumns={@JoinColumn(name="idMarket")})
    private List<Market> ListMarches;
}
