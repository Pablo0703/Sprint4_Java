package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.TipoMotoDTO;
import br.com.fiap.Challenge.Service.TipoMotoService;
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
class TipoMotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoMotoService tipoMotoService;

    // ✅ Caso 1 — listar tipos de moto
    @Test
    @WithMockUser
    void deveListarTiposMotoComSucesso() throws Exception {
        TipoMotoDTO dto = new TipoMotoDTO();
        dto.setId(1L);
        dto.setDescricao("Scooter");
        dto.setCategoria("Urbana");

        when(tipoMotoService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/tiposmotos"))
                .andExpect(status().isOk())
                .andExpect(view().name("tiposmotos/listar"))
                .andExpect(model().attributeExists("tiposmotos"))
                .andExpect(model().attribute("tiposmotos", List.of(dto)));
    }

    // ✅ Caso 2 — salvar tipo de moto
    @Test
    @WithMockUser
    void deveSalvarTipoMotoComSucesso() throws Exception {
        mockMvc.perform(post("/tiposmotos/salvar")
                        .param("descricao", "Scooter")
                        .param("categoria", "Urbana")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tiposmotos"));
    }

    // ✅ Caso 3 — excluir tipo de moto
    @Test
    @WithMockUser
    void deveExcluirTipoMotoComSucesso() throws Exception {
        mockMvc.perform(get("/tiposmotos/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tiposmotos"));
    }
}
