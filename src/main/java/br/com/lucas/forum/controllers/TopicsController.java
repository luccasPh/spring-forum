package br.com.lucas.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lucas.forum.controllers.dtos.ListTopicDto;
import br.com.lucas.forum.controllers.dtos.ShowTopicDto;
import br.com.lucas.forum.controllers.form.CreateTopicForm;
import br.com.lucas.forum.controllers.form.UpdateTopicForm;
import br.com.lucas.forum.models.Topic;
import br.com.lucas.forum.models.User;
import br.com.lucas.forum.repositories.CoursesRepository;
import br.com.lucas.forum.repositories.TopicsRepository;

@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping
    @Cacheable(value = "listTopics")
    public List<ListTopicDto> listTopics(@RequestParam(required = false) String courseName,
            @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        if (courseName == null) {
            Page<Topic> topics = topicsRepository.findAll(pageable);
            return ListTopicDto.convert(topics);
        }

        Page<Topic> topics = topicsRepository.findByCourseName(courseName, pageable);

        return ListTopicDto.convert(topics);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<ShowTopicDto> createTopic(@RequestBody @Valid CreateTopicForm createTopicForm,
            UriComponentsBuilder uriBuilder) {
        Topic newTopic = createTopicForm.convert(coursesRepository);
        User user = getAuthorizedUser();

        newTopic.setAuthor(user);
        topicsRepository.save(newTopic);

        URI uri = uriBuilder.path("/api/topics/{id}").buildAndExpand(newTopic.getId()).toUri();

        return ResponseEntity.created(uri).body(new ShowTopicDto(newTopic));
    }

    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/{id}")
    public ShowTopicDto showTopic(@PathVariable Long id) {
        Optional<Topic> result = topicsRepository.findById(id);
        if (!result.isPresent())
            throw new IllegalArgumentException("topic with id " + id + " not found");

        return new ShowTopicDto(result.get());
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<ShowTopicDto> updateTopic(@PathVariable Long id,
            @RequestBody @Valid UpdateTopicForm updateTopicForm) {
        Optional<Topic> result = topicsRepository.findById(id);
        if (!result.isPresent())
            throw new IllegalArgumentException("topic with id " + id + " not found");

        Topic topic = updateTopicForm.convert(id, topicsRepository);

        return ResponseEntity.ok(new ShowTopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<Object> deleteTopicForm(@PathVariable Long id) {
        Optional<Topic> result = topicsRepository.findById(id);
        if (!result.isPresent())
            throw new IllegalArgumentException("topic with id " + id + " not found");

        topicsRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
