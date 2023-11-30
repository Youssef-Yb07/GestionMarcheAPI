package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.Enumerations.StateTask;
import com.example.gestionmarcheapi.Entity.Tache;
import com.example.gestionmarcheapi.Repository.TacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    public Tache SaveTask(Tache tache){
        return tacheRepository.save(tache);
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
            throw new IllegalStateException("Task not found");
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
            throw new IllegalStateException("Task Not Found !!");
        }
    }

}
