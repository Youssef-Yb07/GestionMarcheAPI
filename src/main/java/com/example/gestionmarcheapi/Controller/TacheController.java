package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
import com.example.gestionmarcheapi.Entity.Tache;
import com.example.gestionmarcheapi.Service.TacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("taches")
@Tag(name = "Tache", description = "API pour la géstion des tâches.")
public class TacheController {

    private final TacheService tacheService;

    @PostMapping("/create")
    @Operation(summary = "Créer une tâche", description = "Créer une tâche")
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        return new ResponseEntity<>(tacheService.SaveTask(tache), HttpStatus.CREATED);
    }
    @GetMapping("/get/all")
    @Operation(summary = "Récupérer toutes les tâches", description = "Récupérer toutes les tâches")
    public ResponseEntity<List<Tache>> getAllTaches() {
        return new ResponseEntity<>(tacheService.getTasks(), HttpStatus.OK);
    }

    @GetMapping("/get")
    @Operation(summary = "Récupérer une tâche", description = "Récupérer une tâche")
    public ResponseEntity<Tache> getTache(@RequestParam int id) {
        return new ResponseEntity<>(tacheService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modifier une tâche", description = "Modifier une tâche")
    public ResponseEntity<Tache> updateTache(@RequestParam int idTask, @RequestBody Tache tache) {
        return new ResponseEntity<>(tacheService.updateTask(idTask,tache), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Supprimer une tâche", description = "Supprimer une tâche")
    public ResponseEntity deleteTache(@RequestParam int id) {
        tacheService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    @Operation(summary = "Supprimer toutes les tâches", description = "Supprimer toutes les tâches")
    public ResponseEntity deleteAllTaches() {
        tacheService.deleteAllTasks();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update/state")
    @Operation(summary = "Modifier l'état d'une tâche", description = "Modifier l'état d'une tâche")
    public ResponseEntity<Tache> updateStateTache(@RequestParam int idTask, @RequestParam StateTask stateTask) {
        return new ResponseEntity<>(tacheService.UpdateStateTask(idTask,stateTask), HttpStatus.OK);
    }


}
