package org.example.ytlearning.Simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Simple.dto.TodoRequest;
import org.example.ytlearning.Simple.service.todoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class TodoRestController {

    @Autowired
    private todoService todoService;


    @PostMapping("v1/todos")
    public ResponseEntity<Object> createTodo(@RequestBody TodoRequest todoRequest){
        log.info("Create todo with Request: {}", todoRequest);
        todoService.create(todoRequest);
        return ResponseEntity.ok().build();
    }

}
