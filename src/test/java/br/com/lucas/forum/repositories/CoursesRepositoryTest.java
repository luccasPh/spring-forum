package br.com.lucas.forum.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import br.com.lucas.forum.models.Course;

@RunWith(SpringRunner.class)
@DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CoursesRepositoryTest {

    @Autowired
    private CoursesRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldReturnCourseByName() {
        String nameCourse = "HTML 5";

        Course html5 = new Course();
        html5.setName(nameCourse);
        html5.setCategory("Programação");
        em.persist(html5);

        Course course = repository.findByName(nameCourse);

        assertNotNull(course);
        assertEquals(nameCourse, course.getName());
    }

    @Test
    public void shouldNotReturnCourseByInvalidName() {
        String nameCourse = "JPA";
        Course course = repository.findByName(nameCourse);

        assertNull(course);
    }
}
