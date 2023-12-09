package com.example.gestionmarcheapi.Entity.DTO;

import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
import com.example.gestionmarcheapi.Entity.Project;
import lombok.Data;
import java.sql.Date;
@Data
public class AddTaskDTO {

    private Integer idTask ;

    private String libelle;

    private Date deadline;

    private String description;

    private String duration;

    private StateTask etat;

    private Project project;

}
