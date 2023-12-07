package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Commentaire;
import com.example.gestionmarcheapi.Service.CommentaireService;
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
@RequestMapping("/commentaires")
@Tag(name = "Commentaires", description = "gestion des commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @PostMapping("/create")
    @Operation(summary = "Créer un commentaire", description = "Enregistrer un commentaire dans la base de donnée")
    public ResponseEntity<Commentaire> CreateCommentaire(@RequestBody Commentaire commentaire) {
        return new ResponseEntity<>(commentaireService.save(commentaire), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    @Operation(summary = "Récupérer tous les commentaires", description = "Récupérer tous les commentaires dans la base de donnée")
    public ResponseEntity<List<Commentaire>> getAllCommentaire() {
        return new ResponseEntity<>(commentaireService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    @Operation(summary = "Récupérer un commentaire", description = "Récupérer un commentaire dans la base de donnée")
    public ResponseEntity<Commentaire> getCommentaireById(@RequestParam int id) {
        return new ResponseEntity<>(commentaireService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Modifier un commentaire", description = "Modifier un commentaire dans la base de donnée")
    public ResponseEntity<Commentaire> updateCommentaire(@RequestParam int idComment,@RequestBody Commentaire commentaire) {
        return new ResponseEntity<>(commentaireService.updateCommentaire(idComment,commentaire), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Supprimer un commentaire", description = "Supprimer un commentaire dans la base de donnée")
    public ResponseEntity deleteCommentaire(@RequestParam int idComment) {
        commentaireService.deleteById(idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/all")
    @Operation(summary = "Supprimer tous les commentaires", description = "Supprimer tous les commentaires dans la base de donnée")
    public ResponseEntity deleteAllCommentaire() {
        commentaireService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
