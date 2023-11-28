package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.User;
import com.example.gestionmarcheapi.ExceptionHandler.ExceptionsHandler;
import com.example.gestionmarcheapi.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@Data
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Users", description = "gestion des utilisateurs")
@RequestMapping(value ="users")
public class UserController {

    private final UserService userService;
    private final ExceptionsHandler exceptionsHandler;

    @Operation(summary= "save new user",description = "Ajouter un nouveau utilisateur dans la base de données")
    @PostMapping(value = "/save")
    public ResponseEntity<User> savedUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @Operation(summary= "update existing user",description = "Modifier un utilisateur existant dans la base de données")
    @PutMapping(value = "/update/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Integer idUser, @RequestBody User user){
        User updateUser = userService.updateUser(idUser,user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @Operation(summary= "get one user",description = "Récupérer un utilisateur existant dans la base de données")
    @GetMapping(value = "/get/{idUser}")
    public ResponseEntity<User> getOneUser(@PathVariable Integer idUser){
        User getUser = userService.getUser(idUser);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }


    @Operation(summary= "get all existing users",description = "Récupérer tous les utilisateurs existants dans la base de données")
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> getAllUser = userService.getAllUsers();
        return new ResponseEntity<>(getAllUser, HttpStatus.OK);
    }


    @Operation(summary= "delete one user",description = "Supprimer un utilisateur existant dans la base de données")
    @DeleteMapping(value = "/delete/{idUser}")
    public ResponseEntity<User> deleteOneUser(@PathVariable Integer idUser){
        User deleteUser = userService.deleteUser(idUser);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }


    @Operation(summary= "delete all existing users",description = "Supprimer tous les utilisateurs existants dans la base de données")
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<List<User>> deleteAllUsers(){
        List<User> deleteAllUsers = userService.deleteAllUsers();
        return new ResponseEntity<>(deleteAllUsers, HttpStatus.OK);
    }

}
