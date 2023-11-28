package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.User;
import com.example.gestionmarcheapi.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //Add User
    public User saveUser(User user) throws EntityNotFoundException{
        user.setNom(user.getNom());
        user.setPrenom(user.getPrenom());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser,User.class);
    }

    //update user
    public User updateUser(Integer idUser,User userEntity) throws EntityNotFoundException{
        Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isPresent()){
            User user = modelMapper.map(userEntity,User.class);
            user.setIdUser(idUser);
            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser,User.class);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public User getUser(Integer idUser) throws EntityNotFoundException{
        Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isPresent()){
            return modelMapper.map(userOptional,User.class);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public List<User> getAllUsers() throws EntityNotFoundException{
        List<User> usersList = userRepository.findAll();
        return usersList.stream().map(element -> modelMapper.map(element, User.class))
                .collect(Collectors.toList());
    }

    public User deleteUser (Integer idUser)  throws EntityNotFoundException{
        Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isPresent()){
             userRepository.delete(userOptional.get());
            return modelMapper.map(userOptional,User.class);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public List<User> deleteAllUsers() throws EntityNotFoundException {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            userRepository.deleteAll();
            return users.stream().map(user -> modelMapper.map(user, User.class))
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("NO user has been found !");
        }
    }
}
