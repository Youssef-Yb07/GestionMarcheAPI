package com.example.gestionmarcheapi.Repository;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.example.gestionmarcheapi.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Project,Integer> {

    List<Project> findByStatusProject(StatusProject statusProject);
    @Query("SELECT p FROM Project p WHERE :givenDate BETWEEN p.dateDebut AND p.dateFin")
    List<Project> findProjectsInDateRange(@Param("givenDate") Date givenDate);

    List<Project> findByBudget(Double budget);

}
