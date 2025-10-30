package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.NotaFiscalDTO;
import br.com.fiap.Challenge.Service.NotaFiscalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class NotaFiscalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaFiscalService notaFiscalService;

    // ✅ Caso 1 — listar notas fiscais
    @Test
    @WithMockUser
    void deveListarNotasFiscaisComSucesso() throws Exception {
        NotaFiscalDTO nota = new NotaFiscalDTO();
        nota.setId(1L);
        nota.setNumero("12345");
        nota.setFornecedor("Mottu LTDA");
        nota.setValorTotal(BigDecimal.valueOf(1500.00));
        nota.setDataEmissao(new Date());

        when(notaFiscalService.listarTodos()).thenReturn(List.of(nota));

        mockMvc.perform(get("/notasfiscais"))
                .andExpect(status().isOk())
                .andExpect(view().name("notasfiscais/listar"))
                .andExpect(model().attributeExists("notasfiscais"))
                .andExpect(model().attribute("notasfiscais", List.of(nota)));
    }

    // ✅ Caso 2 — salvar nota fiscal
    @Test
    @WithMockUser
    void deveSalvarNotaFiscalComSucesso() throws Exception {
        mockMvc.perform(post("/notasfiscais/salvar")
                        .param("numero", "12345")
                        .param("serie", "A1")
                        .param("modelo", "55")
                        .param("valorTotal", "1500.00")
                        .param("fornecedor", "Mottu LTDA")
                        .param("cnpjFornecedor", "12345678000190")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notasfiscais"));
    }

    // ✅ Caso 3 — excluir nota fiscal
    @Test
    @WithMockUser
    void deveExcluirNotaFiscalComSucesso() throws Exception {
        mockMvc.perform(get("/notasfiscais/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notasfiscais"));
    }
}
