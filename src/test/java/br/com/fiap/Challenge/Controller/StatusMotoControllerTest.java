package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.StatusMotoDTO;
import br.com.fiap.Challenge.Service.StatusMotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class StatusMotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusMotoService statusMotoService;

    // ✅ Caso 1 — listar status das motos
    @Test
    @WithMockUser
    void deveListarStatusMotosComSucesso() throws Exception {
        StatusMotoDTO dto = new StatusMotoDTO();
        dto.setId(1L);
        dto.setDescricao("Disponível");
        dto.setDisponivel("true"); // ✅ Corrigido

        when(statusMotoService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/statusmotos"))
                .andExpect(status().isOk())
                .andExpect(view().name("statusmotos/listar"))
                .andExpect(model().attributeExists("statusmotos"))
                .andExpect(model().attribute("statusmotos", List.of(dto)));
    }

    // ✅ Caso 2 — salvar status da moto
    @Test
    @WithMockUser
    void deveSalvarStatusMotoComSucesso() throws Exception {
        mockMvc.perform(post("/statusmotos/salvar")
                        .param("descricao", "Disponível")
                        .param("disponivel", "true")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/statusmotos"));
    }

    // ✅ Caso 3 — excluir status da moto
    @Test
    @WithMockUser
    void deveExcluirStatusMotoComSucesso() throws Exception {
        mockMvc.perform(get("/statusmotos/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/statusmotos"));
    }
}
