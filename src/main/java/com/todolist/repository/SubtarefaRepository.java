package com.todolist.repository;

import com.todolist.model.Subtarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtarefaRepository extends JpaRepository<Subtarefa, Long> {
    List<Subtarefa> findByTarefaId(Long tarefaId);
}
