package br.com.lucas.forum.controllers.dtos;

import br.com.lucas.forum.models.Course;

public class CourseDto {

    private Long id;

    private String category;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.category = course.getCategory();
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

}
