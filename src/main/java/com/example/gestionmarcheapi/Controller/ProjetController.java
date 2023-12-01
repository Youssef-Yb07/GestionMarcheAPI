package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.example.gestionmarcheapi.Entity.Project;
import com.example.gestionmarcheapi.Entity.DTO.ProjectDTO;
import com.example.gestionmarcheapi.Entity.ResponseData;
import com.example.gestionmarcheapi.Service.ProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.FileSystemException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("projets")
@RequiredArgsConstructor
@Tag(name = "Projets", description = "gestion des Projets")
public class ProjetController {

    private final ProjetService projetService;

    @Operation(summary = "Créer un projet", description = "Créer un projet")
    @PostMapping(value = "/create")
    public ResponseEntity<ProjectDTO> CreateProject(@RequestBody ProjectDTO project) {
        return new ResponseEntity<>(projetService.CreateProject(project), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifier un projet", description = "Modifier un projet")
    @PutMapping(value = "/update")
    public ResponseEntity<Project> UpdateProject(@RequestParam Integer idProject, @RequestBody Project project) {
        return new ResponseEntity<>(projetService.UpdateProject(idProject, project), HttpStatus.OK);
    }

    @Operation(summary = "Récupérer un projet", description = "Récupérer un projet")
    @GetMapping(value = "/get")
    public ResponseEntity<Project> getProjectById(@RequestParam Integer idProject) {
        return new ResponseEntity<>(projetService.getProjectById(idProject), HttpStatus.OK);
    }

    @Operation(summary = "Récupérer tous les projets", description = "Récupérer tous les projets")
    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Project>> getAllProject() {
        return new ResponseEntity<>(projetService.getAllProject(), HttpStatus.OK);
    }

    @Operation(summary = "Supprimer un projet", description = "Supprimer un projet")
    @DeleteMapping(value = "/delete")
    public ResponseEntity DeleteProject(@RequestParam Integer idProject) {
        projetService.DeleteProject(idProject);
        return new ResponseEntity<>("Projet supprimé", HttpStatus.OK);
    }

    @Operation(summary = "Supprimer tous les projets", description = "Supprimer tous les projets")
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity DeleteAllProject() {
        projetService.DeleteAllProject();
        return new ResponseEntity<>("Tous les projets ont été supprimés", HttpStatus.OK);
    }

    @Operation(summary = "Modifier l'état d'un projet", description = "Modifier l'état d'un projet")
    @PatchMapping(value = "/update/etat")
    public ResponseEntity<Project> UpdateEtatProject(@RequestParam Integer idProject, @RequestParam StatusProject etat) {
        return new ResponseEntity<>(projetService.updateStatusProjet(idProject, etat), HttpStatus.OK);
    }

    @Operation(summary = "Affecter un budget à un projet", description = "Affecter un budget à un projet")
    @PatchMapping(value = "/update/budget")
    public ResponseEntity<Project> AffecterBudgetToProject(@RequestParam Integer idUser, @RequestParam Integer idProject, @RequestParam Double budget) {
        return new ResponseEntity<>(projetService.AffecterBudgetToProject(idUser, idProject, budget), HttpStatus.OK);
    }


    @Operation(summary = "Upload un fichier CDC", description = "Upload un fichier CDC")
    @PostMapping(value = "/upload/cdc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Project> uploadCDCFile(@RequestParam Integer IdProject, @RequestPart(value = "file") MultipartFile file) {

        try {
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
            new ResponseData(file.getOriginalFilename(), downloadURl, file.getContentType(), file.getSize());

            return new ResponseEntity<>(projetService.uploadCDCFile(IdProject, file), HttpStatus.OK);
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Download un fichier CDC", description = "Download un fichier CDC")
    @GetMapping("/download/cdc")
    public ResponseEntity<Resource> downloadCDCFile(@RequestParam Integer IdProject) {
        Project project = projetService.getProjectById(IdProject);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + project.getFileName() + "\"")
                .body(new ByteArrayResource(project.getCDC_file()));
    }

    @Operation(summary = "Lister les projets par statut", description = "Lister les projets par statut")
    @GetMapping(value = "/get/etat")
    public ResponseEntity<List<Project>> ListerProjetParStatut(@RequestParam StatusProject statusProject) {
        return new ResponseEntity<>(projetService.ListerProjetParStatut(statusProject), HttpStatus.OK);
    }

    @Operation(summary = "Lister les projets par date", description = "Lister les projets par date")
    @GetMapping(value = "/get/date")
    public ResponseEntity<List<Project>> ListerProjetParDate(@RequestParam Date date) {
        return new ResponseEntity<>(projetService.ListerProjetParDate(date), HttpStatus.OK);
    }

    @Operation(summary = "Assigner des tâches à un projet", description = "Assigner des tâches à un projet")
    @PatchMapping(value = "/assign/tasks")
    public ResponseEntity<Project> AssignerTachesAProjet(@RequestParam Integer idProject, @RequestParam List<Integer> idTasks) {
        return new ResponseEntity<>(projetService.assignTasksToProject(idProject, idTasks), HttpStatus.OK);
    }

    @Operation(summary = "Lister les projets par budget", description = "Lister les projets par budget")
    @GetMapping(value = "/get/budget")
    public ResponseEntity<List<Project>> ListerProjetParBudget(@RequestParam Double budget) {
        return new ResponseEntity<>(projetService.findByBudget(budget), HttpStatus.OK);
    }
    @Operation(summary = "Assigner les utilisateur à un projet", description = "Assigner les utilisateur à un projet")
    @PatchMapping(value = "/assign/users")
    public ResponseEntity<Project> AssignUsersToProject(@RequestParam Integer idProject, @RequestParam List<Integer> idUsers) {
        return new ResponseEntity<>(projetService.AssignUsersToProject(idProject,idUsers),HttpStatus.OK);
    }

    @Operation(summary = "Récupérer les projets par utilisateur",description = "Lister les projets par utilisateur")
    @GetMapping("/get/projects/user")
    public ResponseEntity<List<Project>>getProjectsByUser(@RequestParam Integer idUser){
        return new ResponseEntity<>(projetService.getProjectsByUser(idUser),HttpStatus.OK);
    }

}
