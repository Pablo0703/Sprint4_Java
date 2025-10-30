package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.MotociclistaDTO;
import br.com.fiap.Challenge.Service.MotociclistaService;
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
class MotociclistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MotociclistaService motociclistaService;

    // ✅ Caso 1 — listar motociclistas
    @Test
    @WithMockUser
    void deveListarMotociclistasComSucesso() throws Exception {
        MotociclistaDTO dto = new MotociclistaDTO();
        dto.setId(1L);
        dto.setNome("Carlos Silva");
        dto.setCpf("12345678900");

        when(motociclistaService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/motociclistas"))
                .andExpect(status().isOk())
                .andExpect(view().name("motociclistas/listar"))
                .andExpect(model().attributeExists("motociclistas"))
                .andExpect(model().attribute("motociclistas", List.of(dto)));
    }

    // ✅ Caso 2 — salvar motociclista
    @Test
    @WithMockUser
    void deveSalvarMotociclistaComSucesso() throws Exception {
        mockMvc.perform(post("/motociclistas/salvar")
                        .param("nome", "Carlos Silva")
                        .param("cpf", "12345678900")
                        .param("cnh", "987654321")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/motociclistas"));
    }

    // ✅ Caso 3 — excluir motociclista
    @Test
    @WithMockUser
    void deveExcluirMotociclistaComSucesso() throws Exception {
        mockMvc.perform(get("/motociclistas/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/motociclistas"));
    }
}
