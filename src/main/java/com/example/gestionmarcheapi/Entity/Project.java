package com.example.gestionmarcheapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.File;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProject;

    @OneToMany(mappedBy = "project")
    private List<Tache> tasks;

    @JsonIgnore
    @Column(name = "CDC", columnDefinition = "VARBINARY(max)")
    private byte[] CDC_file;

    private String fileName;

    @JsonIgnore
    private String fileType,filePath;

}
