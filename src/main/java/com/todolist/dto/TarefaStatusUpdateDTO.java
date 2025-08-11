package com.todolist.dto;

import com.todolist.model.StatusTarefa;
import jakarta.validation.constraints.NotNull;

public record TarefaStatusUpdateDTO(
        @NotNull StatusTarefa status
) {
}
