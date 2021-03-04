package ru.zhigalin.restapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String email;
    private Set<String> roles;
}
