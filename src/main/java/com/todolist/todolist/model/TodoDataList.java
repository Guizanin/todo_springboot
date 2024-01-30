package com.todolist.todolist.model;

import jakarta.validation.constraints.NotNull;

public record TodoDataList(
        Long id, String title, String description, Boolean checked){
    public TodoDataList(Todo td){
        this(td.getId(), td.getTitle(), td.getDescription(), td.getChecked());
    }
}
