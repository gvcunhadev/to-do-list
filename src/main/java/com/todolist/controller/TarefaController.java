package com.todolist.controller;

import com.todolist.dto.TarefaFiltroDTO;
import com.todolist.dto.TarefaRequestDTO;
import com.todolist.dto.TarefaResponseDTO;
import com.todolist.dto.TarefaStatusUpdateDTO;
import com.todolist.model.Prioridade;
import com.todolist.model.StatusTarefa;
import com.todolist.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponseDTO criar(@RequestBody @Valid TarefaRequestDTO dto) {
        return tarefaService.criarTarefa(dto);
    }

    @GetMapping
    public Page<TarefaResponseDTO> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataVencimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        StatusTarefa statusEnum = null;
        if (status != null) {
            try {
                statusEnum = StatusTarefa.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status de tarefa inválido: " + status);
            }
        }
        Prioridade prioridadeEnum = null;
        if (prioridade != null) {
            try {
                prioridadeEnum = Prioridade.valueOf(prioridade.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Prioridade de tarefa inválida: " + prioridade);
            }
        }
        TarefaFiltroDTO filtro = new TarefaFiltroDTO(statusEnum, prioridadeEnum, dataVencimento);
        return tarefaService.listarTarefas(filtro, PageRequest.of(page, size));
    }


    @GetMapping("/{id}")
    public TarefaResponseDTO buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id);
    }

    @PatchMapping("/{id}/status")
    public TarefaResponseDTO atualizarStatus(
            @PathVariable Long id,
            @RequestBody @Valid TarefaStatusUpdateDTO dto
    ) {
        return tarefaService.atualizarStatus(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
    }
}