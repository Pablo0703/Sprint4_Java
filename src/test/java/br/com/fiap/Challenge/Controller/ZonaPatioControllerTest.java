package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Service.ZonaPatioService;
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
class ZonaPatioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZonaPatioService zonaPatioService;

    // ✅ Caso 1 — listar zonas de pátio
    @Test
    @WithMockUser
    void deveListarZonasComSucesso() throws Exception {
        ZonaPatioDTO dto = new ZonaPatioDTO();
        dto.setId(1L);
        dto.setNomeZona("Zona A");
        dto.setTipoZona("Carga");
        dto.setCapacidade(10);

        when(zonaPatioService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/zonapatios"))
                .andExpect(status().isOk())
                .andExpect(view().name("zonapatios/listar"))
                .andExpect(model().attributeExists("zonapatios"))
                .andExpect(model().attribute("zonapatios", List.of(dto)));
    }

    // ✅ Caso 2 — salvar zona de pátio
    @Test
    @WithMockUser
    void deveSalvarZonaComSucesso() throws Exception {
        mockMvc.perform(post("/zonapatios/salvar")
                        .param("nomeZona", "Zona A")
                        .param("tipoZona", "Carga")
                        .param("capacidade", "10")
                        .param("idPatio", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/zonapatios"));
    }

    // ✅ Caso 3 — excluir zona de pátio
    @Test
    @WithMockUser
    void deveExcluirZonaComSucesso() throws Exception {
        mockMvc.perform(get("/zonapatios/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/zonapatios"));
    }
}
