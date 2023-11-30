package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.example.gestionmarcheapi.Entity.Project;
import com.example.gestionmarcheapi.Entity.User;
import com.example.gestionmarcheapi.Repository.ProjetRepository;
import com.example.gestionmarcheapi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepository projetRepository;

    public Project CreateProject(Project project){
        return projetRepository.save(project);
    }

    public Project UpdateProject(Integer idProject,Project project){
        Project p = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));
            project.setIdProject(p.getIdProject());
            return projetRepository.save(project);

    }

    public Project getProjectById(Integer idProject){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));
        return project;
    }
    public List<Project> getAllProject(){
        return projetRepository.findAll();
    }

    public void DeleteProject(Integer idProject){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));
            projetRepository.delete(project);
        }
    public void DeleteAllProject(){
       List<Project> projects=projetRepository.findAll();
       if(projects.isEmpty()){
           projetRepository.deleteAll();
       }
       else{
           throw new IllegalStateException("Aucun projet existant");
       }
    }

    public Project updateStatusProjet(Integer idProject,StatusProject statusProject){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));
            project.setStatusProject(statusProject);
            return projetRepository.save(project);
    }

    private final UserRepository userRepository;
    public Project AffecterBudgetToProject(Integer idUser,Integer idProject,Double budget){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Projet inexistant avec l'ID : " + idProject));

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idUser));

            if(user.getRole().getLibelle().equals("Chef de projet") || user.getRole().getLibelle().equals("Directeur")){
                project.setBudget(budget);
                return projetRepository.save(project);
            }
            else{
                throw new IllegalStateException("Vous n'avez pas le droit d'effectuer cette action");
            }
    }
    public Project uploadCDCFile(Integer IdProject, MultipartFile file) throws IOException {
        Project project = projetRepository.findById(IdProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + IdProject));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileType = file.getContentType();

        if (fileName.contains("..")) {
            throw new IllegalStateException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        project.setCDC_file(file.getBytes());
        project.setFileName(fileName);
        project.setFileType(fileType);

        return projetRepository.save(project);
    }

}
