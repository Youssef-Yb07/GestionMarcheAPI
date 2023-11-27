package com.example.gestionmarcheapi.Repository;

import com.example.gestionmarcheapi.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */
@Repository
public interface TacheRepository extends JpaRepository<Tache,Integer> {
}
