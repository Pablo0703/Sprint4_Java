package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.StatusOperacaoDTO;
import br.com.fiap.Challenge.Service.StatusOperacaoService;
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

@SuppressWarnings("removal") // remove o aviso do MockBean
@SpringBootTest
@AutoConfigureMockMvc
class StatusOperacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusOperacaoService statusOperacaoService;

    // ✅ Caso 1 — listar status de operação
    @Test
    @WithMockUser
    void deveListarStatusOperacaoComSucesso() throws Exception {
        StatusOperacaoDTO dto = new StatusOperacaoDTO();
        dto.setId(1L);
        dto.setDescricao("Em andamento");
        dto.setTipoMovimentacao("Entrada");

        when(statusOperacaoService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/statusoperacoes"))
                .andExpect(status().isOk())
                .andExpect(view().name("statusoperacoes/listar"))
                .andExpect(model().attributeExists("statusoperacoes"))
                .andExpect(model().attribute("statusoperacoes", List.of(dto)));
    }

    // ✅ Caso 2 — salvar status de operação
    @Test
    @WithMockUser
    void deveSalvarStatusOperacaoComSucesso() throws Exception {
        mockMvc.perform(post("/statusoperacoes/salvar")
                        .param("descricao", "Em andamento")
                        .param("tipoMovimentacao", "Entrada")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/statusoperacoes"));
    }

    // ✅ Caso 3 — excluir status de operação
    @Test
    @WithMockUser
    void deveExcluirStatusOperacaoComSucesso() throws Exception {
        mockMvc.perform(get("/statusoperacoes/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/statusoperacoes"));
    }
}
