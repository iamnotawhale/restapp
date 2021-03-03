package ru.zhigalin.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhigalin.restapp.model.Role;
import ru.zhigalin.restapp.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByRoleName(String roleName) {
        return repository.findByRole(roleName);

    }


}
