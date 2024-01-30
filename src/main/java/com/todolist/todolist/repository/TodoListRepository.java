package com.todolist.todolist.repository;

import com.todolist.todolist.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAllByCheckedFalse(Pageable paginacao);
    Page<Todo> findAllByCheckedTrue(Pageable paginacao);
}
