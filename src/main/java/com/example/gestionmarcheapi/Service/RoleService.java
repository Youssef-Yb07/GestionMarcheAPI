package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.Role;
import com.example.gestionmarcheapi.Repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Data
public class RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    /**
     * ADD ROLE
     *
     * @param role
     * @return
     */

    public Role saveRole(Role role) throws EntityNotFoundException {
        role.setIdRole(role.getIdRole());
        role.setLibelle(role.getLibelle());
        Role saved = roleRepository.save(role);
        return modelMapper.map(saved, Role.class);
    }

    public Role updateRole(Role role, int idRole) throws EntityNotFoundException {
        Optional<Role> roleOptional = roleRepository.findById(idRole);
        if (roleOptional.isPresent()) {
            Role role1 = modelMapper.map(role, Role.class);
            role1.setIdRole(idRole);
            Role updated = roleRepository.save(role1);
            return modelMapper.map(updated, Role.class);
        } else {
            throw new EntityNotFoundException("not found");
        }
    }


    /**
     * GET ROLE BY ID
     *
     * @param id
     * @return
     */
    public Role getRole(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()) {
            return modelMapper.map(roleRepository.findById(id), Role.class);
        }else {
            throw new EntityNotFoundException("Role "+ id +" not found ");
        }
    }

    /**
     * GET ALL ROLES
     *
     * @return
     * @throws EntityNotFoundException
     */
    public List<Role> getAllRoles() throws EntityNotFoundException {
        return roleRepository.findAll().stream().map(element -> modelMapper.map(element, Role.class))
                .collect(Collectors.toList());
    }

    /**
     * DELETE ROLE BY ID
     *
     * @param id
     * @return
     * @throws EntityNotFoundException
     */

    public Role deleteRole(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            roleRepository.deleteById(id);
            return modelMapper.map(roleOptional, Role.class);
        } else {
            System.out.println("this role does not exist !");
            throw new EntityNotFoundException();
        }
    }

    /**
     * DELETE ALL ROLES BY ID
     *
     * @return
     * @throws EntityNotFoundException
     */
    public List<Role> deleteAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        if (!roleList.isEmpty()) {
            roleRepository.deleteAll();
            return Collections.singletonList(modelMapper.map(roleList, Role.class));
        } else {
            System.out.println("The roles list is empty");
            throw new EntityNotFoundException();
        }
    }
}
