package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.MotociclistaDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Entity.MotociclistaEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import br.com.fiap.Challenge.Repository.MotociclistaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MotociclistaServiceTest {

    @Mock
    private MotociclistaRepository motociclistaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private MotociclistaService motociclistaService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarMotociclistas() {
        MotociclistaEntity entity = new MotociclistaEntity();
        entity.setId(1L);
        entity.setNome("Carlos Silva");

        when(motociclistaRepository.findAllByOrderByIdAsc()).thenReturn(List.of(entity));

        var lista = motociclistaService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getNome()).isEqualTo("Carlos Silva");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarMotociclistaPorId() {
        MotociclistaEntity entity = new MotociclistaEntity();
        entity.setId(1L);
        entity.setNome("João Almeida");

        when(motociclistaRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = motociclistaService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getNome()).isEqualTo("João Almeida");
    }

    // ✅ Caso 3 — salvar motociclista
    @Test
    void deveSalvarMotociclista() {
        MotociclistaDTO dto = new MotociclistaDTO();
        dto.setId(1L);
        dto.setNome("Pedro Souza");
        dto.setCpf("12345678900");
        dto.setCnh("1234567890");
        dto.setDataValidadeCnh(LocalDate.now().plusYears(2));
        dto.setTelefone("11987654321");
        dto.setEmail("pedro@teste.com");
        dto.setAtivo("S");

        MotociclistaEntity entity = new MotociclistaEntity();
        entity.setId(1L);
        entity.setNome("Pedro Souza");

        when(enderecoRepository.findById(any())).thenReturn(Optional.of(new EnderecoEntity()));
        when(motociclistaRepository.save(any(MotociclistaEntity.class))).thenReturn(entity);

        var salvo = motociclistaService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Pedro Souza");
    }

    // ✅ Caso 4 — excluir motociclista
    @Test
    void deveExcluirMotociclista() {
        when(motociclistaRepository.existsById(1L)).thenReturn(true);

        motociclistaService.excluir(1L);

        verify(motociclistaRepository, times(1)).deleteById(1L);
    }
}
