    package com.example.gestionmarcheapi.Entity;
    import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
    import jakarta.persistence.*;
    import lombok.Data;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Data
    public class Tache {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idTask ;

        private Date deadline;

        private String description;

        private String duration;

        @Enumerated(EnumType.STRING)
        private StateTask etat;

        @ManyToOne
        @JoinColumn(name = "idProjet")
        private Project project;

        @OneToMany
        @JoinTable(name="tache_commentaire_association",
                joinColumns={@JoinColumn(name="idTask")},
                inverseJoinColumns={@JoinColumn(name="idCommentaire")})
        private List<Commentaire> commentairesList;

    }
