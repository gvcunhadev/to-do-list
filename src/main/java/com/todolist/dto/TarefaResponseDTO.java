package com.todolist.dto;

import com.todolist.model.Prioridade;
import com.todolist.model.StatusTarefa;

import java.time.LocalDate;
import java.util.List;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        LocalDate dataVencimento,
        StatusTarefa status,
        Prioridade prioridade,
        List<SubtarefaResponseDTO> subtarefas
) {
}
