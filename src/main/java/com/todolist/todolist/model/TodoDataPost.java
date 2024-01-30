package com.todolist.todolist.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoDataPost(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Boolean checked
) {}
