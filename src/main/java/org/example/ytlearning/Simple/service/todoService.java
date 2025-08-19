package org.example.ytlearning.Simple.service;

import org.example.ytlearning.Simple.dto.TodoRequest;
import org.example.ytlearning.Simple.dto.TodoResponse;

import java.util.List;

public interface todoService {

    void create(TodoRequest todoRequest);
    void update(Long id, TodoRequest todoRequest);
    void delete(Long id);

    TodoResponse getById(Long id);
    List<TodoResponse> getAll();
    List<TodoResponse> getByTitle(String title);
}
