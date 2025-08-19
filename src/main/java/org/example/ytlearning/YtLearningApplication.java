package org.example.ytlearning;

import jakarta.annotation.PostConstruct;
import org.example.ytlearning.Simple.model.Todo;
import org.example.ytlearning.Simple.repository.TodoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.ytlearning.Simple.repository")

public class YtLearningApplication {

    private final TodoRepository todoRepository;

    public YtLearningApplication(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(YtLearningApplication.class, args);
    }

    @PostConstruct
    void todo() {
        Todo todo = new Todo();
        todo.setTitle("Learn Spring Boot");
        todo.setDescription("Learn Spring Boot from scratch");
        todo.setCreatedDate(java.time.LocalDateTime.now());

        todoRepository.save(todo);

        todoRepository.findById(1L)
                .ifPresent(System.out::println);

        todoRepository.findFirstByTitle("Learn Spring Boot")
                .ifPresent(System.out::println);
    }
}