package com.example.gestionmarcheapi.Repository;

import com.example.gestionmarcheapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u JOIN Role r ON u.role.idRole = r.idRole WHERE r.libelle = 'Directeur' AND u.idUser NOT IN (SELECT p.directeur.idUser FROM Project p WHERE p.directeur.idUser IS NOT NULL)")
    List<User> getDirectorsNotAffectedInProjects();

    @Query("SELECT u FROM User u JOIN Role r ON u.role.idRole = r.idRole WHERE r.libelle = 'Chef de service' AND u.idUser NOT IN (SELECT p.ChefService.idUser FROM Project p WHERE p.ChefService.idUser IS NOT NULL)")
    List<User> getChiefServicesNotAffectedInProjects();
    @Query("SELECT u FROM User u JOIN Role r ON u.role.idRole = r.idRole WHERE r.libelle = 'Employé'")
    List<User> getAllEmployees();
    User findByEmailAndPassword(String email, String password);

}
