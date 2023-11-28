package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Role;
import com.example.gestionmarcheapi.ExceptionHandler.ExceptionsHandler;
import com.example.gestionmarcheapi.Service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yassine Deriouch
 * @project Projet gestion des marchés
 */

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "roles")
@CrossOrigin("*")
@Tag(name = "Roles", description = "gestion des roles")
public class RoleController {
    
    private final RoleService roleService;
    private final ExceptionsHandler exceptionsHandler;


    @Operation(summary= "save new role",description = "Ajouter un role dans la base de données")
    @PostMapping(value = "/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role savedRole = roleService.saveRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
    

    @Operation(summary= "update existing role",description = "Modifier un role existant dans la base de données")
    @PutMapping(value = "/update/{idRole}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int idRole){
        Role updatedRole = roleService.updateRole(role,idRole);
        return new ResponseEntity<>(roleService.updateRole(updatedRole,idRole), HttpStatus.OK);
    }


    @Operation(summary= "get one role",description = "Récupérer un role existant dans la base de données")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Role> getOneRole(@PathVariable int id){
        Role role = roleService.getRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @Operation(summary= "get all existing roles",description = "Récupérer tous les roles existants dans la base de données")
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> rolesList = roleService.getAllRoles();
        return new ResponseEntity<>(rolesList, HttpStatus.OK);
    }

    @Operation(summary = "delete one role", description = "Supprimer un role existant dans la base de données")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Role> deleteOneRole(@PathVariable int id) {
        Role role = roleService.deleteRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @Operation(summary = "delete all existing roles", description = "Supprimer tous les roles existants dans la base de données")
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<List<Role>> deleteAllRoles() {
        List<Role> rolesList = roleService.deleteAllRoles();
        return new ResponseEntity<>(rolesList, HttpStatus.OK);
    }

}
