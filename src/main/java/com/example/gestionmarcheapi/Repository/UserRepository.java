package com.example.gestionmarcheapi.Repository;

import com.example.gestionmarcheapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
