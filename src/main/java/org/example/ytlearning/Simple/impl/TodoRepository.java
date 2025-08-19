package org.example.ytlearning.Simple.impl;

import org.example.ytlearning.Simple.model.Todo;
import org.springframework.data.repository.Repository;

interface TodoRepository extends Repository<Todo, Long> {
}
