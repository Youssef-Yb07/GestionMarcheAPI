package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Role;
import com.example.gestionmarcheapi.ExceptionHandler.ExceptionsHandler;
import com.example.gestionmarcheapi.Service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary= "save new role")
    @PostMapping(value = "/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role savedRole = roleService.saveRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }
    

    @Operation(summary= "update existing role")
    @PutMapping(value = "/update/{idRole}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int idRole){
        Role updatedRole = roleService.updateRole(role,idRole);
        return new ResponseEntity<>(roleService.updateRole(updatedRole,idRole), HttpStatus.OK);
    }


    @Operation(summary= "get one role")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Role> getOneRole(@PathVariable int id){
        Role role = roleService.getRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @Operation(summary= "get all existing roles")
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> rolesList = roleService.getAllRoles();
        return new ResponseEntity<>(rolesList, HttpStatus.OK);
    }

    @Operation(summary = "delete one role")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Role> deleteOneRole(@PathVariable int id) {
        Role role = roleService.deleteRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @Operation(summary = "delete all existing roles")
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<List<Role>> deleteAllRoles() {
        List<Role> rolesList = roleService.deleteAllRoles();
        return new ResponseEntity<>(rolesList, HttpStatus.OK);
    }

}
