package br.com.lucas.forum.controllers.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.lucas.forum.models.Topic;
import br.com.lucas.forum.models.TopicStatus;

public class ShowTopicDto {
    private Long id;

    private String title;

    private String message;

    private LocalDateTime createdDate;

    private UserDto user;

    private List<AnswerDto> answers;

    private CourseDto course;

    private TopicStatus status;

    public ShowTopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdDate = topic.getCreatedDate();
        this.status = topic.getStatus();
        this.user = topic.getAuthor() != null ? new UserDto(topic.getAuthor()) : null;
        this.course = new CourseDto(topic.getCourse());
        this.answers = topic.getAnswers()
                .stream()
                .map(AnswerDto::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public UserDto getUser() {
        return user;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public CourseDto getCourse() {
        return course;
    }

    public TopicStatus getStatus() {
        return status;
    }

}
