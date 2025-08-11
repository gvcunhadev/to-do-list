package com.todolist.dto;

import com.todolist.model.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubtarefaRequestDTO(
        @NotBlank  String descricao,
        @NotNull StatusTarefa status) {
}
