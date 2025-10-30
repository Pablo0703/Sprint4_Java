package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.TipoMotoDTO;
import br.com.fiap.Challenge.Entity.TipoMotoEntity;
import br.com.fiap.Challenge.Repository.TipoMotoRepository;
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
class TipoMotoServiceTest {

    @Mock
    private TipoMotoRepository tipoMotoRepository;

    @InjectMocks
    private TipoMotoService tipoMotoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarTiposMoto() {
        TipoMotoEntity entity = new TipoMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Scooter");
        entity.setCategoria("Leve");

        when(tipoMotoRepository.findAll()).thenReturn(List.of(entity));

        var lista = tipoMotoService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getDescricao()).isEqualTo("Scooter");
        assertThat(lista.get(0).getCategoria()).isEqualTo("Leve");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarTipoPorId() {
        TipoMotoEntity entity = new TipoMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Naked");
        entity.setCategoria("Esportiva");

        when(tipoMotoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = tipoMotoService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getDescricao()).isEqualTo("Naked");
        assertThat(dto.getCategoria()).isEqualTo("Esportiva");
    }

    // ✅ Caso 3 — salvar tipo de moto
    @Test
    void deveSalvarTipoMoto() {
        TipoMotoDTO dto = new TipoMotoDTO();
        dto.setId(1L);
        dto.setDescricao("Street");
        dto.setCategoria("Urbana");

        TipoMotoEntity entity = new TipoMotoEntity();
        entity.setId(1L);
        entity.setDescricao("Street");
        entity.setCategoria("Urbana");

        when(tipoMotoRepository.save(any(TipoMotoEntity.class))).thenReturn(entity);

        var salvo = tipoMotoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getDescricao()).isEqualTo("Street");
        assertThat(salvo.getCategoria()).isEqualTo("Urbana");
    }

    // ⚠️ Caso 4 — tentar excluir inexistente
    @Test
    void deveLancarExcecaoAoExcluirTipoInexistente() {
        when(tipoMotoRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> tipoMotoService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Tipo de moto não encontrado para excluir");
    }

    // ✅ Caso 5 — excluir tipo existente
    @Test
    void deveExcluirTipoMoto() {
        when(tipoMotoRepository.existsById(1L)).thenReturn(true);

        tipoMotoService.excluir(1L);

        verify(tipoMotoRepository, times(1)).deleteById(1L);
    }
}
