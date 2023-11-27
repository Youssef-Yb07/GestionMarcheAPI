package com.example.gestionmarcheapi.Repository;

import com.example.gestionmarcheapi.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */
@Repository
public interface ProjetRepository extends JpaRepository<Project,Integer> {
}
