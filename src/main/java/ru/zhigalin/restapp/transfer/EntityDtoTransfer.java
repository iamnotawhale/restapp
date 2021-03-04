package ru.zhigalin.restapp.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zhigalin.restapp.dto.UserDto;
import ru.zhigalin.restapp.model.Role;
import ru.zhigalin.restapp.model.User;
import ru.zhigalin.restapp.service.RoleService;

import java.util.stream.Collectors;

@Component
public class EntityDtoTransfer {

    private final RoleService roleService;

    @Autowired
    public EntityDtoTransfer(RoleService roleService) {
        this.roleService = roleService;
    }


    public User fromDto(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles() == null ? null : userDto.getRoles().stream()
                .map(roleService::findByRoleName)
                .collect(Collectors.toSet())
        );
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles() == null ? null : user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet()));
        return dto;
    }
}
