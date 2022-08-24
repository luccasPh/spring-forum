package br.com.lucas.forum.controllers.dtos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.lucas.forum.models.Topic;

public class ListTopicDto {

    private Long id;

    private String title;

    private String message;

    private LocalDateTime createdDate;

    public ListTopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdDate = topic.getCreatedDate();
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

    public static List<ListTopicDto> convert(Page<Topic> pageTopics) {
        return pageTopics.map(ListTopicDto::new).getContent();
    }

}
