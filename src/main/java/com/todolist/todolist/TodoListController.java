package com.todolist.todolist;

import com.todolist.todolist.model.Todo;
import com.todolist.todolist.model.TodoDataList;
import com.todolist.todolist.model.TodoDataPost;
import com.todolist.todolist.model.TodoDataUpdate;
import com.todolist.todolist.repository.TodoListRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/todo")
public class TodoListController {

    @Autowired
    TodoListRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postTodo(@RequestBody @Valid TodoDataPost dataPost, UriComponentsBuilder uriBuilder){
        var todo = new Todo(dataPost);
        repository.save(todo);

        var uri = uriBuilder.path("/todo/{id}").buildAndExpand(todo.getId()).toUri();

        return ResponseEntity.created(uri).body(new TodoDataList(todo));
    }

    @GetMapping("/list/unchecked")
    public ResponseEntity<Page<TodoDataList>> listTodoUnchecked(Pageable pagination){
       var page = repository.findAllByCheckedFalse(pagination).map(TodoDataList::new);

       return ResponseEntity.ok(page);
    }

    @GetMapping("/list/checked")
    public ResponseEntity<Page<TodoDataList>> listTodoChecked(Pageable pagination){
       var page = repository.findAllByCheckedTrue(pagination).map(TodoDataList::new);

       return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDataList> listTodoId(@PathVariable Long id){
       var findedTodo = repository.getReferenceById(id);

       return ResponseEntity.ok(new TodoDataList(findedTodo));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTodoData(@RequestBody @Valid TodoDataUpdate dataTodo){
        var todo = repository.getReferenceById(dataTodo.id());
        todo.updateInformations(dataTodo);

        return ResponseEntity.ok(new TodoDataList(todo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removeTodo(@PathVariable Long id){
       repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
