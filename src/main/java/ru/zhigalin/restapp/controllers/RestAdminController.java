package ru.zhigalin.restapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.zhigalin.restapp.model.User;
import ru.zhigalin.restapp.service.RoleService;
import ru.zhigalin.restapp.service.UserService;
import ru.zhigalin.restapp.dto.UserDto;
import ru.zhigalin.restapp.transfer.EntityDtoTransfer;

import java.util.List;


@RestController
@RequestMapping("/rest_admin")
public class RestAdminController {

    private final UserService userService;

    private final RoleService roleService;

    final
    PasswordEncoder bCryptPasswordEncoder;

    private final EntityDtoTransfer entityDtoTransfer;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService, PasswordEncoder bCryptPasswordEncoder, EntityDtoTransfer entityDtoTransfer) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.entityDtoTransfer = entityDtoTransfer;
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
        User user = entityDtoTransfer.fromDto(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
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
        User user = entityDtoTransfer.fromDto(userDto);
        user.setId(id);
        user.setPassword(userDto.getPassword());
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
