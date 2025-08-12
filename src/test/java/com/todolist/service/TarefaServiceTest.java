package com.todolist.service;


import com.todolist.dto.TarefaRequestDTO;
import com.todolist.dto.TarefaStatusUpdateDTO;
import com.todolist.model.StatusTarefa;
import com.todolist.model.Subtarefa;
import com.todolist.model.Tarefa;
import com.todolist.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

     @Mock
    private TarefaRepository tarefaRepository;

     @InjectMocks
    private TarefaService tarefaService;

     @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
     }

    @Test
    void deveSalvarTarefaComSucesso() {
        TarefaRequestDTO dto = new TarefaRequestDTO("Estudar Spring", "Aprender testes", LocalDate.now(), StatusTarefa.PENDENTE, null, null);
        Tarefa tarefaParaSalvar = new Tarefa();
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaParaSalvar);

        tarefaService.criarTarefa(dto);

        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveLancarExcecaoAoConcluirComSubtarefasPendentes() {
        Long idTarefa = 1L;

        TarefaStatusUpdateDTO dto = new TarefaStatusUpdateDTO(StatusTarefa.CONCLUIDA);


        Subtarefa subtarefaPendente = Subtarefa.builder().status(StatusTarefa.PENDENTE).build();
        Tarefa tarefaComSubtarefaPendente = Tarefa.builder().subtarefas(List.of(subtarefaPendente)).build();


        when(tarefaRepository.findById(idTarefa)).thenReturn(Optional.of(tarefaComSubtarefaPendente));

        assertThrows(IllegalStateException.class, () -> tarefaService.atualizarStatus(idTarefa, dto));

        verify(tarefaRepository, never()).save(any());
    }
}
