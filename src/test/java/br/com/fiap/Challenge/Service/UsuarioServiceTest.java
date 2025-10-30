package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.Entity.UsuarioEntity;
import br.com.fiap.Challenge.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    // ✅ Caso 1 — cadastrar usuário com sucesso
    @Test
    void deveCadastrarUsuarioComSenhaCriptografada() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(1L);
        usuario.setUsername("pablo");
        usuario.setEmail("pablo@fiap.com");
        usuario.setPassword("123456");

        // Mocka comportamento do PasswordEncoder e do repository
        when(passwordEncoder.encode("123456")).thenReturn("senhaEncriptada123");
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuario);

        var salvo = usuarioService.cadastrar("pablo", "pablo@fiap.com", "123456");

        assertThat(salvo).isNotNull();
        assertThat(salvo.getUsername()).isEqualTo("pablo");
        verify(passwordEncoder, times(1)).encode("123456");
        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }

    // ✅ Caso 2 — verificar se senha foi encriptada antes de salvar
    @Test
    void deveEncriptarSenhaAntesDeSalvar() {
        when(passwordEncoder.encode("minhaSenha")).thenReturn("hash123");

        usuarioService.cadastrar("usuarioTeste", "teste@fiap.com", "minhaSenha");

        verify(passwordEncoder, times(1)).encode("minhaSenha");
        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }
}
