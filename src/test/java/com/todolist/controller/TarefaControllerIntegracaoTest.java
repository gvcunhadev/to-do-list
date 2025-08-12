package com.todolist.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.dto.TarefaRequestDTO;
import com.todolist.model.Prioridade;
import com.todolist.model.StatusTarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
 class TarefaControllerIntegracaoTest {
 @Autowired
 private MockMvc mockMvc;

 @Autowired
 private ObjectMapper objectMapper;

 @Test
 void deveCriarTarefaComSucessoQuandoDadosValidos() throws Exception {
  TarefaRequestDTO dto = new TarefaRequestDTO("Tarefa de Teste", "Descrição", LocalDate.now(), StatusTarefa.PENDENTE, Prioridade.ALTA, null);

  mockMvc.perform(post("/api/tarefas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(dto)))
          .andExpect(status().isCreated());
 }
}
