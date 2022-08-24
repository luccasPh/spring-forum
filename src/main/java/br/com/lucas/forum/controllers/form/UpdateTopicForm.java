package br.com.lucas.forum.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.lucas.forum.models.Topic;
import br.com.lucas.forum.repositories.TopicsRepository;

public class UpdateTopicForm {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 150)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 500)
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic convert(Long id, TopicsRepository topicsRepository) {
        Topic topic = topicsRepository.findById(id).get();
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }
}
