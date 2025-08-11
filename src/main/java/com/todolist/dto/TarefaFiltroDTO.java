package com.todolist.dto;

import com.todolist.model.Prioridade;
import com.todolist.model.StatusTarefa;

import java.time.LocalDate;

public record TarefaFiltroDTO(
        StatusTarefa status,
        Prioridade prioridade,
        LocalDate dataVencimento
) {
}
