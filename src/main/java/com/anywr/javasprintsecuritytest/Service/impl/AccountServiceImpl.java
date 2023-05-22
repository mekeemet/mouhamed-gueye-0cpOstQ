package com.anywr.javasprintsecuritytest.Service.impl;


import com.anywr.javasprintsecuritytest.Entity.Personn;
import com.anywr.javasprintsecuritytest.Entity.Role;
import com.anywr.javasprintsecuritytest.Repository.PersonnRepository;
import com.anywr.javasprintsecuritytest.Repository.RoleRepository;
import com.anywr.javasprintsecuritytest.Service.Interface.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private PersonnRepository personneRepository;
    private RoleRepository appRoleRepository;

    public AccountServiceImpl(PersonnRepository personneRepository, RoleRepository appRoleRepository) {
        this.personneRepository = personneRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public Personn savePersonn(Personn personn) {
        Personn personnSaved=this.personneRepository.save(personn);
        addRoleToUser(personn.getUsername(),"ADMIN");
        return personnSaved;
    }

    @Override
    public Role saveRole(Role role) {
        return appRoleRepository.save(role);
    }

    @Override
    public Personn loadUserByUsername(String username) {
        return personneRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        Personn appUser=personneRepository.findByUsername(username);
        Role appRole=appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }


}