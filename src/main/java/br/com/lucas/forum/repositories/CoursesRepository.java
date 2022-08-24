package br.com.lucas.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.forum.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {

    Course findByName(String nameCourse);
}
