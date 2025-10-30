package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.StatusOperacaoDTO;
import br.com.fiap.Challenge.Entity.StatusOperacaoEntity;
import br.com.fiap.Challenge.Repository.StatusOperacaoRepository;
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
class StatusOperacaoServiceTest {

    @Mock
    private StatusOperacaoRepository statusOperacaoRepository;

    @InjectMocks
    private StatusOperacaoService statusOperacaoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarStatusOperacoes() {
        StatusOperacaoEntity entity = new StatusOperacaoEntity();
        entity.setId(1L);
        entity.setDescricao("Em andamento");
        entity.setTipoMovimentacao("Entrada");

        when(statusOperacaoRepository.findAll()).thenReturn(List.of(entity));

        var lista = statusOperacaoService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getDescricao()).isEqualTo("Em andamento");
        assertThat(lista.get(0).getTipoMovimentacao()).isEqualTo("Entrada");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarStatusOperacaoPorId() {
        StatusOperacaoEntity entity = new StatusOperacaoEntity();
        entity.setId(1L);
        entity.setDescricao("Saída de pátio");
        entity.setTipoMovimentacao("Saída");

        when(statusOperacaoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = statusOperacaoService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getDescricao()).isEqualTo("Saída de pátio");
        assertThat(dto.getTipoMovimentacao()).isEqualTo("Saída");
    }

    // ✅ Caso 3 — salvar status de operação
    @Test
    void deveSalvarStatusOperacao() {
        StatusOperacaoDTO dto = new StatusOperacaoDTO();
        dto.setId(1L);
        dto.setDescricao("Em análise");
        dto.setTipoMovimentacao("Interna");

        StatusOperacaoEntity entity = new StatusOperacaoEntity();
        entity.setId(1L);
        entity.setDescricao("Em análise");
        entity.setTipoMovimentacao("Interna");

        when(statusOperacaoRepository.save(any(StatusOperacaoEntity.class))).thenReturn(entity);

        var salvo = statusOperacaoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getDescricao()).isEqualTo("Em análise");
    }

    // ⚠️ Caso 4 — tentar excluir inexistente
    @Test
    void deveLancarExcecaoAoExcluirInexistente() {
        when(statusOperacaoRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> statusOperacaoService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Status de operação não encontrado para excluir");
    }

    // ✅ Caso 5 — excluir existente
    @Test
    void deveExcluirStatusOperacao() {
        when(statusOperacaoRepository.existsById(1L)).thenReturn(true);

        statusOperacaoService.excluir(1L);

        verify(statusOperacaoRepository, times(1)).deleteById(1L);
    }
}
