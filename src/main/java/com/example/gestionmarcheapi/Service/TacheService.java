package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.DTO.AddTaskDTO;
import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
import com.example.gestionmarcheapi.Entity.Project;
import com.example.gestionmarcheapi.Entity.Tache;
import com.example.gestionmarcheapi.Entity.User;
import com.example.gestionmarcheapi.Repository.ProjetRepository;
import com.example.gestionmarcheapi.Repository.TacheRepository;
import com.example.gestionmarcheapi.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    public AddTaskDTO SaveTask(AddTaskDTO task){
        Tache tache=new Tache();
        tache.setIdTask(task.getIdTask());
        tache.setLibelle(task.getLibelle());
        tache.setDeadline(task.getDeadline());
        tache.setDescription(task.getDescription());
        tache.setDuration(task.getDuration());
        tache.setEtat(task.getEtat());
        tache.setProject(task.getProject());
        tacheRepository.save(tache);
        return task;
    }
    public Tache getTaskById(int id){
        return tacheRepository.findById(id).orElse(null);
    }
    public List<Tache> getTasks(){
        return tacheRepository.findAll();
    }
    public void deleteTask(int id){
        Optional<Tache>opt_task=tacheRepository.findById(id);
        if(opt_task.isPresent()){
            tacheRepository.deleteById(id);
        }
        else{
            throw new IllegalStateException("Task not found");
        }
    }
    public void deleteAllTasks(){
        List<Tache>tasks=tacheRepository.findAll();
        if(tasks.size()>0){
            tacheRepository.deleteAll();
        }
        else{
            throw new IllegalStateException("No tasks found");
        }
    }

    public Tache updateTask(Integer idTask,Tache tache){
        Optional<Tache>opt_task=tacheRepository.findById(idTask);
        if(opt_task.isPresent()){
            Tache task=opt_task.get();
            tache.setIdTask(task.getIdTask());
            return tacheRepository.save(task);
        }
        else{
            throw new EntityNotFoundException("Task not found");
        }
    }
    public Tache UpdateStateTask(int idTask, StateTask stateTask){
        Optional<Tache> optionalTache=tacheRepository.findById(idTask);

        if(optionalTache.isPresent()){
            Tache tache=optionalTache.get();
            tache.setEtat(stateTask);
            return tacheRepository.save(tache);
        }
        else {
            throw new EntityNotFoundException("Task Not Found !!");
        }
    }

    private final ProjetRepository projetRepository;
    public Tache AssignTaskToProject(int idTask, int idProject){
        Optional<Tache> optionalTache=tacheRepository.findById(idTask);
        Optional<Project> optionalProject=projetRepository.findById(idProject);

        if(optionalTache.isPresent() && optionalProject.isPresent()){
            Tache tache=optionalTache.get();
            Project project=optionalProject.get();
            tache.setProject(project);
            return tacheRepository.save(tache);
        }
        else {
            throw new EntityNotFoundException("Task Not Found !!");
        }
    }
    public List<Tache> getTasksNotAffectedToProject(){
        List<Tache> tasksNotfAffected=new ArrayList<>();
        for(Tache tache:tacheRepository.findAll()){
            if(tache.getProject()==null){
                tasksNotfAffected.add(tache);
            }
        }
        return tasksNotfAffected;
    }
    public List<Tache> getTasksNotAffectedToUser(){
        List<Tache> tasksNotfAffected=new ArrayList<>();
        for(Tache tache:tacheRepository.findAll()){
            if(tache.getEmployee()==null){
                tasksNotfAffected.add(tache);
            }
        }
        return tasksNotfAffected;
    }

    private final UserRepository userRepository;
    public Tache AssignTacheToEmployee(Integer idTache, Integer idEmployee){
        Tache tache=tacheRepository.findById(idTache)
                .orElseThrow(() -> new IllegalStateException("Tache not found with id: " + idTache));
        User user=userRepository.findById(idEmployee)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idEmployee));
        tache.setEmployee(user);
        return tacheRepository.save(tache);
    }

    private final ProjetRepository projectRepository;

    public List<Tache> getTasksFromProjectByEmployee(int idProject, int idEmployee){
        List<Tache> tasks=new ArrayList<>();
        Project project=projectRepository.findById(idProject)
                .orElseThrow(() -> new IllegalStateException("Projet inexistant avec l'ID : " + idProject));
        User employee=userRepository.findById(idEmployee)
                .orElseThrow(() -> new IllegalStateException("Utilisateur inexistant avec l'ID : " + idEmployee));

        for(Tache tache:project.getTasks()){
            if(tache.getEmployee().getIdUser()==employee.getIdUser()){
                tasks.add(tache);
            }
        }
        return tasks;
    }
    public Map<String, Long> getTacheCountByStatus() {
        List<Tache> taches = tacheRepository.findAll();

        return taches.stream()
                .collect(Collectors.groupingBy(tache -> tache.getEtat().name(), Collectors.counting()));
    }



}
