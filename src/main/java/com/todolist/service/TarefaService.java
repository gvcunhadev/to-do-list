package com.todolist.service;

import com.todolist.dto.*;
import com.todolist.model.StatusTarefa;
import com.todolist.model.Subtarefa;
import com.todolist.model.Tarefa;
import com.todolist.repository.SubtarefaRepository;
import com.todolist.repository.TarefaRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final SubtarefaRepository subtarefaRepository;

    @Transactional
    public TarefaResponseDTO criarTarefa(TarefaRequestDTO dto) {
        Tarefa tarefa = Tarefa.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .dataVencimento(dto.dataVencimento())
                .status(dto.status())
                .prioridade(dto.prioridade())
                .build();

        List<Subtarefa> subTarefasSalvas = new ArrayList<>();
        if (dto.subtarefas() != null) {
            for (SubtarefaRequestDTO subDto : dto.subtarefas()) {
                Subtarefa sub = Subtarefa.builder()
                        .descricao(subDto.descricao())
                        .status(subDto.status())
                        .tarefa(tarefa)
                        .build();
                subTarefasSalvas.add(sub);
            }
        }
        tarefa.setSubtarefas(subTarefasSalvas);
        Tarefa salva = tarefaRepository.save(tarefa);
        return toResponseDTO(salva);
    }

    public List<TarefaResponseDTO> listarTarefas(TarefaFiltroDTO filtro) {
        Specification<Tarefa> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtro.status() != null) {
                predicates.add(cb.equal(root.get("status"), filtro.status()));
            }
            if (filtro.prioridade() != null) {
                predicates.add(cb.equal(root.get("prioridade"), filtro.prioridade()));
            }
            if (filtro.dataVencimento() != null) {
                predicates.add(cb.equal(root.get("dataVencimento"), filtro.dataVencimento()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return tarefaRepository.findAll(spec)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TarefaResponseDTO buscarPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return toResponseDTO(tarefa);
    }

    @Transactional
    public TarefaResponseDTO atualizarStatus(Long id, TarefaStatusUpdateDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (dto.status() == StatusTarefa.CONCLUIDA) {
            boolean sePendente = tarefa.getSubtarefas().stream()
                    .anyMatch(s -> s.getStatus() != StatusTarefa.CONCLUIDA);
            if (sePendente) {
                throw new IllegalStateException("Não é possível concluir a tarefa com subtarefas pendentes.");
            }
        }
        tarefa.setStatus(dto.status());
        tarefaRepository.save(tarefa);
        return toResponseDTO(tarefa);
    }

    @Transactional
    public void deletarTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    private TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        List<SubtarefaResponseDTO> subtarefas = tarefa.getSubtarefas() == null
                ? List.of()
                : tarefa.getSubtarefas().stream()
                .map(s -> new SubtarefaResponseDTO(s.getId(), s.getDescricao(), s.getStatus()))
                .toList();
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataVencimento(),
                tarefa.getStatus(),
                tarefa.getPrioridade(),
                subtarefas
        );
    }
}
