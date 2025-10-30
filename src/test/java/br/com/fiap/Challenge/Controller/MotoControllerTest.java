package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.Service.MotoService;
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
class MotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MotoService motoService;

    // ✅ Caso 1 — listar motos
    @Test
    @WithMockUser
    void deveListarMotosComSucesso() throws Exception {
        MotoDTO moto = new MotoDTO();
        moto.setId(1L);
        moto.setPlaca("XYZ1234");
        moto.setModelo("Honda CG 160");
        moto.setCor("Preta");

        when(motoService.listarTodos()).thenReturn(List.of(moto));

        mockMvc.perform(get("/motos"))
                .andExpect(status().isOk())
                .andExpect(view().name("motos/listar"))
                .andExpect(model().attributeExists("motos"))
                .andExpect(model().attribute("motos", List.of(moto)));
    }

    // ✅ Caso 2 — salvar moto
    @Test
    @WithMockUser
    void deveSalvarMotoComSucesso() throws Exception {
        mockMvc.perform(post("/motos/salvar")
                        .param("placa", "XYZ1234")
                        .param("modelo", "Honda CG 160")
                        .param("cor", "Preta")
                        .param("anoFabricacao", "2024")
                        .param("anoModelo", "2025")
                        .param("chassi", "9C2KC0810HR123456")
                        .param("cilindrada", "160")
                        .param("valorAquisicao", "18000.00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/motos"));
    }

    // ✅ Caso 3 — excluir moto
    @Test
    @WithMockUser
    void deveExcluirMotoComSucesso() throws Exception {
        mockMvc.perform(get("/motos/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/motos"));
    }
}
