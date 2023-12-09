    package com.example.gestionmarcheapi.Entity;
    import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.Data;
    import java.sql.Date;
    import java.util.List;

    @Entity
    @Data
    public class Tache {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idTask ;

        private String libelle;

        private Date deadline;

        private String description;

        private String duration;

        @Enumerated(EnumType.STRING)
        private StateTask etat;

        @ManyToOne
        @JoinColumn(name = "idProjet")
        @JsonIgnore
        private Project project;

        @OneToMany(mappedBy = "task")
        private List<Commentaire> commentaires;

        @ManyToOne
        @JoinColumn(name = "idEmployee")
        private User employee;


    }
