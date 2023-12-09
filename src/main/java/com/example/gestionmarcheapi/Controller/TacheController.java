package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.DTO.AddTaskDTO;
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
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("taches")
@Tag(name = "Tache", description = "API pour la géstion des tâches.")
public class TacheController {

    private final TacheService tacheService;
    @PostMapping("/create")
    @Operation(summary = "Créer une tâche", description = "Créer une tâche")
    public ResponseEntity<AddTaskDTO> createTache(@RequestBody AddTaskDTO tache) {
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
    @PatchMapping("/assign/to/project")
    @Operation(summary = "Assigner une tâche à un projet", description = "Assigner une tâche à un projet")
    public ResponseEntity<Tache> assignTaskToProject(@RequestParam int idTask, @RequestParam int idProject) {
        return new ResponseEntity<>(tacheService.AssignTaskToProject(idTask,idProject), HttpStatus.OK);
    }
    @GetMapping("/get/not/affected/to/project")
    @Operation(summary = "Récupérer les tâches non affectées à un projet", description = "Récupérer les tâches non affectées à un projet")
    public ResponseEntity<List<Tache>> getTasksNotAffectedToProject() {
        return new ResponseEntity<>(tacheService.getTasksNotAffectedToProject(), HttpStatus.OK);
    }

    @GetMapping("/get/not/affected/to/employee")
    @Operation(summary = "Récupérer les tâches non affectées à un employe", description = "Récupérer les tâches non affectées à un Employe")
    public ResponseEntity<List<Tache>> getTasksNotAffectedToEmployee() {
        return new ResponseEntity<>(tacheService.getTasksNotAffectedToUser(), HttpStatus.OK);
    }
    @PatchMapping("/assign/to/employee")
    @Operation(summary = "Assigner une tâche à un employé", description = "Assigner une tâche à un employé")
    public ResponseEntity<Tache> assignTaskToEmployee(@RequestParam int idTask, @RequestParam int idEmployee) {
        return new ResponseEntity<>(tacheService.AssignTacheToEmployee(idTask,idEmployee), HttpStatus.OK);
    }

    @GetMapping("/get/from/project/by/employee")
    @Operation(summary = "Récupérer les tâches d'un projet par employé", description = "Récupérer les tâches d'un projet par employé")
    public ResponseEntity<List<Tache>> getTasksFromProjectByEmployee(@RequestParam int idProject, @RequestParam int idEmployee) {
        return new ResponseEntity<>(tacheService.getTasksFromProjectByEmployee(idProject,idEmployee), HttpStatus.OK);
    }
    @GetMapping("/get/count/by/status")
    @Operation(summary = "Récupérer le nombre de tâches par état", description = "Récupérer le nombre de tâches par état")
    public ResponseEntity<Map<String,Long>> getTacheCountByStatus() {
        return new ResponseEntity<>(tacheService.getTacheCountByStatus(), HttpStatus.OK);
    }


}
