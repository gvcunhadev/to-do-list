package com.todolist.dto;

import com.todolist.model.StatusTarefa;

public record SubtarefaResponseDTO(
        Long id,
        String descricao,
        StatusTarefa status
) {
}
