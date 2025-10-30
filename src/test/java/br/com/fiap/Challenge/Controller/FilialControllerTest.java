package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.FilialDTO;
import br.com.fiap.Challenge.Service.FilialService;
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
class FilialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilialService filialService;

    // ✅ Caso 1 — listar filiais
    @Test
    @WithMockUser
    void deveListarFiliaisComSucesso() throws Exception {
        FilialDTO filial = new FilialDTO();
        filial.setId(1L);
        filial.setNome("Filial São Paulo");

        when(filialService.listarTodos()).thenReturn(List.of(filial));

        mockMvc.perform(get("/filiais"))
                .andExpect(status().isOk())
                .andExpect(view().name("filiais/listar"))
                .andExpect(model().attributeExists("filiais"))
                .andExpect(model().attribute("filiais", List.of(filial)));
    }

    // ✅ Caso 2 — salvar filial
    @Test
    @WithMockUser
    void deveSalvarFilialComSucesso() throws Exception {
        mockMvc.perform(post("/filiais/salvar")
                        .param("nome", "Filial São Paulo")
                        .param("cnpj", "12345678000190")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/filiais"));
    }

    // ✅ Caso 3 — excluir filial
    @Test
    @WithMockUser
    void deveExcluirFilialComSucesso() throws Exception {
        mockMvc.perform(get("/filiais/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/filiais"));
    }
}