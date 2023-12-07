package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Column
    private String libelle;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<User> users;
}
