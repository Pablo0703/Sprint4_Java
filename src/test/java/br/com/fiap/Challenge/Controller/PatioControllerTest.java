package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.Service.PatioService;
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
class PatioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatioService patioService;

    // ✅ Caso 1 — listar pátios
    @Test
    @WithMockUser
    void deveListarPatiosComSucesso() throws Exception {
        PatioDTO dto = new PatioDTO();
        dto.setId(1L);
        dto.setNome("Pátio Central");
        dto.setAreaM2(250.0);
        dto.setCapacidade(30);
        dto.setAtivo("true");

        when(patioService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/patios"))
                .andExpect(status().isOk())
                .andExpect(view().name("patios/listar"))
                .andExpect(model().attributeExists("patios"))
                .andExpect(model().attribute("patios", List.of(dto)));
    }

    // ✅ Caso 2 — salvar pátio
    @Test
    @WithMockUser
    void deveSalvarPatioComSucesso() throws Exception {
        mockMvc.perform(post("/patios/salvar")
                        .param("nome", "Pátio Central")
                        .param("capacidade", "30")
                        .param("areaM2", "250.0")
                        .param("ativo", "true")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patios"));
    }

    // ✅ Caso 3 — excluir pátio
    @Test
    @WithMockUser
    void deveExcluirPatioComSucesso() throws Exception {
        mockMvc.perform(get("/patios/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patios"));
    }
}
