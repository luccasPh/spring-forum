package br.com.lucas.forum.controllers.dtos;

import br.com.lucas.forum.models.User;

public class UserDto {

    private Long id;

    private String name;

    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
