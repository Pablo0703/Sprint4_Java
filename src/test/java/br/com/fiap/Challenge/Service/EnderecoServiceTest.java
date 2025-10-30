package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.EnderecoDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    // ✅ Caso 1 — listar endereços
    @Test
    void deveListarEnderecos() {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(1L);
        entity.setLogradouro("Rua Teste");

        when(enderecoRepository.findAll()).thenReturn(List.of(entity));

        var lista = enderecoService.listarTodos();
        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getLogradouro()).isEqualTo("Rua Teste");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarEnderecoPorId() {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(1L);
        entity.setLogradouro("Rua Central");

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = enderecoService.buscarPorId(1L);
        assertThat(dto).isNotNull();
        assertThat(dto.getLogradouro()).isEqualTo("Rua Central");
    }

    // ✅ Caso 3 — salvar endereço
    @Test
    void deveSalvarEndereco() {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setLogradouro("Rua Nova");

        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(1L);
        entity.setLogradouro("Rua Nova");

        when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(entity);

        var salvo = enderecoService.salvar(dto);
        assertThat(salvo).isNotNull();
        assertThat(salvo.getLogradouro()).isEqualTo("Rua Nova");
    }

    // ✅ Caso 4 — excluir endereço
    @Test
    void deveExcluirEndereco() {
        when(enderecoRepository.existsById(1L)).thenReturn(true);
        enderecoService.excluir(1L);
        verify(enderecoRepository, times(1)).deleteById(1L);
    }
}
