package org.example.ytlearning.Simple.repository;

import org.example.ytlearning.Simple.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

    Optional<Todo> findFirstByTitle(String title);

    List<Todo>findAllByTitleContaining(String title);

}
