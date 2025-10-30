package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.Entity.UsuarioEntity;
import br.com.fiap.Challenge.Service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    // ✅ Caso 1 — cadastrar usuário
    @Test
    @WithMockUser
    void deveCadastrarUsuarioComSucesso() throws Exception {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(1L);
        usuario.setUsername("Pablo");
        usuario.setEmail("pablo@fiap.com");

        when(usuarioService.cadastrar("Pablo", "pablo@fiap.com", "123456"))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios/salvar")
                        .param("username", "Pablo")
                        .param("email", "pablo@fiap.com")
                        .param("password", "123456")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuarios"));
    }
}
