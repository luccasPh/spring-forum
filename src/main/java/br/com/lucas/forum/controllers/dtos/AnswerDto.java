package br.com.lucas.forum.controllers.dtos;

import java.time.LocalDateTime;

import br.com.lucas.forum.models.Answer;

public class AnswerDto {

    private Long id;

    private String message;

    private LocalDateTime creationDate;

    private UserDto author;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.creationDate = answer.getCreationDate();
        this.author = new UserDto(answer.getAuthor());
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public UserDto getAuthor() {
        return author;
    }

}
