package ru.zhigalin.restapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.zhigalin.restapp.model.Role;
import ru.zhigalin.restapp.model.User;
import ru.zhigalin.restapp.service.RoleService;
import ru.zhigalin.restapp.service.UserService;
import ru.zhigalin.restapp.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest_admin")
public class RestAdminController {

    private final UserService userService;

    private final RoleService roleService;

    final
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> allUser() {
        List<User> userList = userService.listUsers();
        return userList != null && !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/user")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") Long id) {
        User user = userService.findUserById(id);

        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = fromDto(userDto);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        User user = fromDto(userDto);
        user.setId(id);
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private User fromDto(UserDto userDto) {
        User user = new User();

        user.setLogin(userDto.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles() == null ? null : userDto.getRoles().stream()
                .map(roleService::findByRoleName)
                .collect(Collectors.toSet())
        );
        return user;
    }

    private static UserDto toDto(User user) {
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
