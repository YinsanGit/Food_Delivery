package org.example.ytlearning.Simple.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Simple.dto.TodoRequest;
import org.example.ytlearning.Simple.dto.TodoResponse;
import org.example.ytlearning.Simple.model.Todo;
import org.example.ytlearning.Simple.repository.TodoRepository;
import org.example.ytlearning.Simple.service.todoService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoServiceImpl implements todoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public void create(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(new TodoRequest().getTitle());
        todo.setDescription(new TodoRequest().getDescription());
        todo.setCompleted(new TodoRequest().getCompleted());
        todo.setCreatedAt(new Date());

        todoRepository.save(todo);
    }

    @Override
    public void update(Long id, TodoRequest todoRequest) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()){
            log.info("Todo with id {} not found", id);
            return;
        }

        Todo todoToUpdate = todo.get();
        todoToUpdate.setTitle(todoRequest.getTitle());
        todoToUpdate.setDescription(todoRequest.getDescription());
        todoToUpdate.setCompleted(todoRequest.getCompleted());
        todoToUpdate.setCreatedAt(new Date());
        todoToUpdate.setUpdatedAt(new Date());

        todoRepository.saveAndFlush(todoToUpdate);
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse getById(Long id) {
        TodoResponse todoResponse = new TodoResponse();
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()){
            log.info("Get todo with id {} not found", id);
        }
        todoResponse.setId(todo.get().getId());
        todoResponse.setTitle(todo.get().getTitle());
        todoResponse.setDescription(todo.get().getDescription());
        todoResponse.setCompleted(todo.get().getCompleted());

        return todoResponse;

    }

    @Override
    public List<TodoResponse> getAll() {
        List<TodoResponse> todoResponses = new ArrayList<>();

        List<Todo> todo = todoRepository.findAll();
        if(todo.isEmpty()){
            log.info("No todo found");
        }
        for (var t : todo){
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setId(t.getId());
            todoResponse.setTitle(t.getTitle());
            todoResponse.setDescription(t.getDescription());
            todoResponse.setCompleted(t.getCompleted());
            todoResponses.add(todoResponse);

        }
        return todoResponses;
    }

    @Override
    public List<TodoResponse> getByTitle(String title) {
        return List.of();
    }
}
