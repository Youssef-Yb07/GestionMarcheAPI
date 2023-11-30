package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.Commentaire;
import com.example.gestionmarcheapi.Repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    public Commentaire save(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }
    public Commentaire findById(Integer id) {
        return commentaireRepository.findById(id).orElse(null);
    }
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }
    public void deleteById(Integer id) {
        Optional<Commentaire> optionalCommentaire= commentaireRepository.findById(id);
        if (optionalCommentaire.isPresent()) {
            commentaireRepository.deleteById(id);
        }
        else {
            throw new IllegalStateException("Commentaire non trouvé");
        }
    }
    public void deleteAll() {
        List<Commentaire> commentaires = commentaireRepository.findAll();

        if (commentaires.size() != 0) {
            commentaireRepository.deleteAll();
        }
        else {
            throw new IllegalStateException("Aucun Commentaire trouvé");
        }
    }
    public Commentaire updateCommentaire(int idCommentaire,Commentaire commentaire) {
        Optional<Commentaire> optionalCommentaire= commentaireRepository.findById(idCommentaire);

        if(optionalCommentaire.isPresent()) {
            Commentaire comment = optionalCommentaire.get();
            commentaire.setIdCommentaire(comment.getIdCommentaire());
            return commentaireRepository.save(commentaire);
        }
        else {
            throw new IllegalStateException("Commentaire non trouvé");
        }
    }

}
