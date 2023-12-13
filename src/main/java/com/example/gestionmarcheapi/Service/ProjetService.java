package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.*;
import com.example.gestionmarcheapi.Entity.DTO.ProjectDTO;
import com.example.gestionmarcheapi.Entity.Enumerations.StatusProject;
import com.example.gestionmarcheapi.Repository.ProjetRepository;
import com.example.gestionmarcheapi.Repository.TacheRepository;
import com.example.gestionmarcheapi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepository projetRepository;
    public ProjectDTO CreateProject(ProjectDTO projectDTO){
        Project project = new Project();
        project.setDateDebut(projectDTO.getDateDebut());
        project.setDateFin(projectDTO.getDateFin());
        project.setBudget(projectDTO.getBudget());
        project.setStatusProject(projectDTO.getStatusProject());
        project.setNom(projectDTO.getNom());
        project.setDescription(projectDTO.getDescription());
        project.setMarket(projectDTO.getMarket());
        projetRepository.save(project);
        return projectDTO;
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

    public Project updateStatusProjet(Integer idProject, StatusProject statusProject){
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

            if(user.getRole().getLibelle().equals("Chef de service") || user.getRole().getLibelle().equals("Directeur")){
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
    public List<Project> ListerProjetParStatut(StatusProject statusProject){
        return projetRepository.findByStatusProject(statusProject);
    }

    public List<Project> ListerProjetParDate(Date date){
        return projetRepository.findProjectsInDateRange(date);
    }

    private final TacheRepository tacheRepository;
    public Project assignTasksToProject(Integer idProject, List<Integer> idTasks) {
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));

        List<Tache> tasks = tacheRepository.findAllById(idTasks);

        tasks.forEach(task -> task.setProject(project));

        project.setTasks(tasks);

        tacheRepository.saveAll(tasks);

        return projetRepository.save(project);
    }

    public List<Project> findByBudget(Double budget){
        return projetRepository.findByBudget(budget);
    }

    public Project AssignUsersToProject(Integer idProject,List<Integer> idUsers){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));

        List<User> users = userRepository.findAllById(idUsers);

        for (User user:users) {

            user.getProjects().add(project);

            if(user.getRole().getLibelle().equals("Chef de service")){
                project.setChefService(user);
            }
            else if(user.getRole().getLibelle().equals("Directeur")){
                project.setDirecteur(user);
            }
            else if(user.getRole().getLibelle().equals("Employe")){
                project.getEmployees().add(user);
            }
        }
        userRepository.saveAll(users);

        return projetRepository.save(project);
    }

   /*public List<Project> getProjectsByUser(Integer idUser){
        User user=userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idUser));
        return user.getProjects();
    }*/
    public Project GetProjectByDirecteurOrChefService(Integer idUser){
        User user=userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idUser));
        for(Project project:projetRepository.findAll()){
            if(project.getChefService().getIdUser().equals(user.getIdUser()) || project.getDirecteur().getIdUser().equals(user.getIdUser())){
                return project;
            }
        }
        throw new IllegalStateException("Vous n'avez pas le droit d'effectuer cette action");
    }

    public Project deleteEmployeFromProject(Integer idProject,Integer idEmp){
        Project project = projetRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Project not found with id: " + idProject));
        User user = userRepository.findById(idEmp)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idEmp));
        if(user.getRole().getLibelle().equals("Employe")){
            project.getEmployees().remove(user);
            return projetRepository.save(project);
        }
        else{
            throw new IllegalStateException("Vous n'avez pas le droit d'effectuer cette action");
        }
    }

    public List<Project> getprojectsByEmloyee(Integer idUser){
        User user=userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idUser));
        List<Project> projects=new ArrayList<>();
        for(Project project:projetRepository.findAll()){
            if(project.getEmployees().contains(user)){
                projects.add(project);
            }
        }
        return projects;
    }

    public Map<String, Long> getProjetCountByStatus() {
        List<Project> projets = projetRepository.findAll();

        return projets.stream()
                .collect(Collectors.groupingBy(project -> project.getStatusProject().name(), Collectors.counting()));
    }

    public Map<String,Integer> getNumberOfEmployeesByProject(){
        Map<String,Integer> map = new HashMap<>();
        for(Project project:projetRepository.findAll()){
            map.put(project.getNom(),project.getEmployees().size());
        }
        return map;
    }

    public Map<String,Integer> getNumberOfTasksByProject(){
        Map<String,Integer> map = new HashMap<>();
        for(Project project:projetRepository.findAll()){
            map.put(project.getNom(),project.getTasks().size());
        }
        return map;
    }

    public Map<String, Long> countTasksByEmployeeInProjects(Integer idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idUser));

        Map<String, Long> tasksCountByProject =new HashMap<>();

        // Récupérer la liste des projets de l'utilisateur
        List<Project> projects = getprojectsByEmloyee(user.getIdUser());

        tasksCountByProject = projects.stream()
                .flatMap(project -> project.getTasks().stream())
                .collect(Collectors.groupingBy(tache -> tache.getProject().getNom(),
                        Collectors.counting()));

        return tasksCountByProject;
    }

}
