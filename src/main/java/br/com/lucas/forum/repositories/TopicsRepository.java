package br.com.lucas.forum.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.forum.models.Topic;

public interface TopicsRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourseName(String courseName, Pageable pageable);
}
