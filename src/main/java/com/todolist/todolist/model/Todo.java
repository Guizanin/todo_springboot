package com.todolist.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Todo")
@Table(name="todos")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String description;
    Boolean checked;



    public Todo(TodoDataPost todo){
        this.title =  todo.title();
        this.description = todo.description();
        this.checked = todo.checked();
    }

    public void updateInformations(TodoDataUpdate td) {
        if(td.checked() != null){
            this.checked = td.checked();
        }

        if(td.description() != null){
            this.description = td.description();
        }

        if(td.title() != null){
            this.title = td.title();
        }
    }
}
