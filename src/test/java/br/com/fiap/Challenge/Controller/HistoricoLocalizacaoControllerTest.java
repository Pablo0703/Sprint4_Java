package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.HistoricoLocalizacaoDTO;
import br.com.fiap.Challenge.Service.HistoricoLocalizacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class HistoricoLocalizacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoricoLocalizacaoService historicoService;

    // ✅ Caso 1 — listar históricos
    @Test
    @WithMockUser
    void deveListarHistoricosComSucesso() throws Exception {
        HistoricoLocalizacaoDTO dto = new HistoricoLocalizacaoDTO();
        dto.setId(1L);
        dto.setKmRodados(BigDecimal.valueOf(120.5));

        when(historicoService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/historicos"))
                .andExpect(status().isOk())
                .andExpect(view().name("historicos/listar"))
                .andExpect(model().attributeExists("historicos"))
                .andExpect(model().attribute("historicos", List.of(dto)));
    }

    // ✅ Caso 2 — salvar histórico
    @Test
    @WithMockUser
    void deveSalvarHistoricoComSucesso() throws Exception {
        mockMvc.perform(post("/historicos/salvar")
                        .param("kmRodados", "120.5")
                        .param("dataHoraEntrada", "2025-10-29T08:00")
                        .param("dataHoraSaida", "2025-10-29T10:00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/historicos"));
    }

    // ✅ Caso 3 — excluir histórico
    @Test
    @WithMockUser
    void deveExcluirHistoricoComSucesso() throws Exception {
        mockMvc.perform(get("/historicos/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/historicos"));
    }
}
