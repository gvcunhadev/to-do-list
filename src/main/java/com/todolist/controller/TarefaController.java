package com.todolist.controller;

import com.todolist.dto.TarefaFiltroDTO;
import com.todolist.dto.TarefaRequestDTO;
import com.todolist.dto.TarefaResponseDTO;
import com.todolist.dto.TarefaStatusUpdateDTO;
import com.todolist.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    public List<TarefaResponseDTO> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade,
            @RequestParam(required = false) String dataVencimento
    ) {
        // Conversão dos parâmetros para o DTO de filtro
        TarefaFiltroDTO filtro = new TarefaFiltroDTO(
                status != null ? com.todolist.model.StatusTarefa.valueOf(status) : null,
                prioridade != null ? com.todolist.model.Prioridade.valueOf(prioridade) : null,
                dataVencimento != null ? java.time.LocalDate.parse(dataVencimento) : null
        );
        return tarefaService.listarTarefas(filtro);
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