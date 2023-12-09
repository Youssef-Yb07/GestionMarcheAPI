package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.DTO.AuthDTO;
import com.example.gestionmarcheapi.Entity.DTO.ResponseAuthDTO;
import com.example.gestionmarcheapi.Entity.User;
import com.example.gestionmarcheapi.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Users", description = "gestion des utilisateurs")
@RequestMapping(value ="users")
public class UserController {

    private final UserService userService;

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

    @Operation(summary = "Récupérer les directeurs non affectés à un projet",description = "Récupérer les directeurs non affectés à un projet")
    @GetMapping("/get/director/not/affected")
    public ResponseEntity<List<User>>getDirectorNotAffectedInProject(){
        return new ResponseEntity<>(userService.getDirectorNotAffectedInProject(),HttpStatus.OK);
    }
    @Operation(summary = "Récupérer les chefs de service non affectés à un projet",description = "Récupérer les chefs de service non affectés à un projet")
    @GetMapping("/get/chiefService/not/affected")
    public ResponseEntity<List<User>>getChiefServicesNotAffectedInProjects(){
        return new ResponseEntity<>(userService.getChiefServiceNotAffectedInProject(),HttpStatus.OK);
    }

    @Operation(summary = "Récupérer la liste des employés",description = "Récupérer les Employés")
    @GetMapping("/get/Employees")
    public ResponseEntity<List<User>>getUsersByRole(){
        return new ResponseEntity<>(userService.getEmployees(),HttpStatus.OK);
    }

    @Operation(summary = "Authentification",description = "Authentification d'un utilisateur")
    @PostMapping("/auth")
    public ResponseEntity<ResponseAuthDTO> auth(@RequestBody AuthDTO authDTO){
        return new ResponseEntity<>(userService.Athentication(authDTO),HttpStatus.OK);
    }
    @GetMapping("/get/Employees/by/project/{idProject}")
    public ResponseEntity<List<User>>getEmployeesByProject(@PathVariable Integer idProject){
        return new ResponseEntity<>(userService.getEmployeesByProject(idProject),HttpStatus.OK);
    }
    @GetMapping("/get/Employees/not/member/in/project/{idProject}")
    public ResponseEntity<List<User>>getEmployeesNotMemberInProject(@PathVariable Integer idProject){
        return new ResponseEntity<>(userService.getEmployeesNotMemberInProject(idProject),HttpStatus.OK);
    }

}
