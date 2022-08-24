package br.com.lucas.forum.controllers.dtos;

public class TokenDto {

    private String token;
    private final String type = "Bearer";

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

}
