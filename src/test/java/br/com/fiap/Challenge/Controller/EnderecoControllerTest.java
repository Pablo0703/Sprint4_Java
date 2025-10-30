package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.EnderecoDTO;
import br.com.fiap.Challenge.Service.EnderecoService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    // ✅ Caso 1 — listar endereços
    @Test
    @WithMockUser
    void deveListarEnderecosComSucesso() throws Exception {
        EnderecoDTO e = new EnderecoDTO();
        e.setId(1L);
        e.setLogradouro("Rua Teste");
        e.setNumero("100");

        when(enderecoService.listarTodos()).thenReturn(List.of(e));

        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk())
                .andExpect(view().name("enderecos/listar"))
                .andExpect(model().attributeExists("enderecos"))
                .andExpect(model().attribute("enderecos", List.of(e)));
    }

    // ✅ Caso 2 — salvar endereço
    @Test
    @WithMockUser
    void deveSalvarEnderecoComSucesso() throws Exception {
        mockMvc.perform(post("/enderecos/salvar")
                        .param("logradouro", "Rua Nova")
                        .param("numero", "123")
                        .param("bairro", "Centro")
                        .param("cep", "00000-000")
                        .param("cidade", "São Paulo")
                        .param("estado", "SP")
                        .param("pais", "Brasil")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/enderecos"));
    }

    // ✅ Caso 3 — excluir endereço
    @Test
    @WithMockUser
    void deveExcluirEnderecoComSucesso() throws Exception {
        mockMvc.perform(get("/enderecos/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/enderecos"));
    }
}
