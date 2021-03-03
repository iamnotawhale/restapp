package ru.zhigalin.restapp.dto;

import java.util.Set;


public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String email;
    private Set<String> roles;

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, String email, Set<String> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstname='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
