package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.LocalizacaoMotoDTO;
import br.com.fiap.Challenge.Service.LocalizacaoMotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class LocalizacaoMotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalizacaoMotoService localizacaoService;

    // ✅ Caso 1 — listar localizações
    @Test
    @WithMockUser
    void deveListarLocalizacoesComSucesso() throws Exception {
        LocalizacaoMotoDTO dto = new LocalizacaoMotoDTO();
        dto.setId(1L);

        // ✅ Ajuste para java.util.Date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        dto.setDataHoraEntrada(calendar.getTime());
        dto.setDataHoraSaida(new Date());

        when(localizacaoService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/localizacoesmoto"))
                .andExpect(status().isOk())
                .andExpect(view().name("localizacoesmoto/listar"))
                .andExpect(model().attributeExists("localizacoesmoto"))
                .andExpect(model().attribute("localizacoesmoto", List.of(dto)));
    }


    // ✅ Caso 2 — salvar localização
    @Test
    @WithMockUser
    void deveSalvarLocalizacaoComSucesso() throws Exception {
        mockMvc.perform(post("/localizacoesmoto/salvar")
                        .param("idMoto", "1")
                        .param("idZona", "2")
                        .param("dataHoraEntrada", "2025-10-29T08:00")
                        .param("dataHoraSaida", "2025-10-29T10:00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/localizacoesmoto"));
    }

    // ✅ Caso 3 — excluir localização
    @Test
    @WithMockUser
    void deveExcluirLocalizacaoComSucesso() throws Exception {
        mockMvc.perform(get("/localizacoesmoto/excluir/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/localizacoesmoto"));
    }
}
