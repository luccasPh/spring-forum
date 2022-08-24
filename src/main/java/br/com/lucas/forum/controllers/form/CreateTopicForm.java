package br.com.lucas.forum.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.lucas.forum.models.Course;
import br.com.lucas.forum.models.Topic;
import br.com.lucas.forum.repositories.CoursesRepository;

public class CreateTopicForm {

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 150)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 500)
    private String message;

    @NotNull
    private Long courseId;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Topic convert(CoursesRepository coursesRepository) {
        Topic newTopic = new Topic();
        newTopic.setTitle(this.title);
        newTopic.setMessage(this.message);

        Course course = coursesRepository.findById(this.courseId).get();
        newTopic.setCourse(course);

        return newTopic;
    }

}
