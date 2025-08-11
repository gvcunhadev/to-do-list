package com.todolist.dto;

import com.todolist.model.Prioridade;
import com.todolist.model.StatusTarefa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TarefaRequestDTO(
        @NotBlank String titulo,
        String descricao,
        @NotNull @FutureOrPresent LocalDate dataVencimento,
        @NotNull StatusTarefa status,
        @NotNull Prioridade prioridade,
        List<SubtarefaRequestDTO> subtarefas
) {
}
