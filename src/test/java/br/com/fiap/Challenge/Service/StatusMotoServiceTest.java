package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.StatusMotoDTO;
import br.com.fiap.Challenge.Entity.StatusMotoEntity;
import br.com.fiap.Challenge.Repository.StatusMotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StatusMotoServiceTest {

    @Mock
    private StatusMotoRepository statusMotoRepository;

    @InjectMocks
    private StatusMotoService statusMotoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarStatusMotos() {
        StatusMotoEntity entity = new StatusMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Disponível");
        entity.setDisponivel("S");

        when(statusMotoRepository.findAll()).thenReturn(List.of(entity));

        var lista = statusMotoService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getDescricao()).isEqualTo("Disponível");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarStatusPorId() {
        StatusMotoEntity entity = new StatusMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Em manutenção");
        entity.setDisponivel("N");

        when(statusMotoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = statusMotoService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getDescricao()).isEqualTo("Em manutenção");
        assertThat(dto.getDisponivel()).isEqualTo("N");
    }

    // ✅ Caso 3 — salvar status
    @Test
    void deveSalvarStatusMoto() {
        StatusMotoDTO dto = new StatusMotoDTO();
        dto.setId(1L);
        dto.setDescricao("Disponível");
        dto.setDisponivel("S");

        StatusMotoEntity entity = new StatusMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Disponível");
        entity.setDisponivel("S");

        when(statusMotoRepository.save(any(StatusMotoEntity.class))).thenReturn(entity);

        var salvo = statusMotoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getDescricao()).isEqualTo("Disponível");
    }

    // ⚠️ Caso 4 — excluir status inexistente
    @Test
    void deveLancarExcecaoAoExcluirStatusInexistente() {
        when(statusMotoRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> statusMotoService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Status da moto não encontrado para excluir");
    }

    // ✅ Caso 5 — excluir status existente
    @Test
    void deveExcluirStatusMoto() {
        when(statusMotoRepository.existsById(1L)).thenReturn(true);

        statusMotoService.excluir(1L);

        verify(statusMotoRepository, times(1)).deleteById(1L);
    }
}
