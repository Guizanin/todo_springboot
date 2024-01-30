package com.todolist.todolist.model;

import jakarta.validation.constraints.NotNull;

public record TodoDataUpdate(
        @NotNull
        Long id,

        String title,
        String description,
        Boolean checked
) {
}
