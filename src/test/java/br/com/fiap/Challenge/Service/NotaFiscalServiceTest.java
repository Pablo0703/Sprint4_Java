package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.NotaFiscalDTO;
import br.com.fiap.Challenge.Entity.NotaFiscalEntity;
import br.com.fiap.Challenge.Repository.NotaFiscalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotaFiscalServiceTest {

    @Mock
    private NotaFiscalRepository notaFiscalRepository;

    @InjectMocks
    private NotaFiscalService notaFiscalService;

    // ✅ Caso 1 — listar todas as notas fiscais
    @Test
    void deveListarNotasFiscais() {
        NotaFiscalEntity entity = new NotaFiscalEntity();
        entity.setId(1L);
        entity.setNumero("NF001");

        when(notaFiscalRepository.findAllByOrderByIdAsc()).thenReturn(List.of(entity));

        var lista = notaFiscalService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getNumero()).isEqualTo("NF001");
    }

    // ✅ Caso 2 — buscar nota por ID
    @Test
    void deveBuscarNotaPorId() {
        NotaFiscalEntity entity = new NotaFiscalEntity();
        entity.setId(1L);
        entity.setNumero("NF002");
        entity.setFornecedor("Mottu Supplier");

        when(notaFiscalRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = notaFiscalService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getNumero()).isEqualTo("NF002");
        assertThat(dto.getFornecedor()).isEqualTo("Mottu Supplier");
    }

    // ✅ Caso 3 — salvar nota fiscal
    @Test
    void deveSalvarNotaFiscal() {
        NotaFiscalDTO dto = new NotaFiscalDTO();
        dto.setId(1L);
        dto.setNumero("NF003");
        dto.setSerie("A1");
        dto.setModelo("Eletrônico");
        dto.setChaveAcesso("123456789ABCDEF");
        dto.setDataEmissao(new java.util.Date());
        dto.setValorTotal(BigDecimal.valueOf(1550.75));
        dto.setFornecedor("Fornecedor XPTO");
        dto.setCnpjFornecedor("12.345.678/0001-90");

        NotaFiscalEntity entity = new NotaFiscalEntity();
        entity.setId(1L);
        entity.setNumero("NF003");

        when(notaFiscalRepository.save(any(NotaFiscalEntity.class))).thenReturn(entity);

        var salvo = notaFiscalService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNumero()).isEqualTo("NF003");
    }

    // ✅ Caso 4 — excluir nota fiscal existente
    @Test
    void deveExcluirNotaFiscal() {
        when(notaFiscalRepository.existsById(1L)).thenReturn(true);

        notaFiscalService.excluir(1L);

        verify(notaFiscalRepository, times(1)).deleteById(1L);
    }

    // ⚠️ Caso 5 — tentar excluir nota inexistente
    @Test
    void deveLancarExcecaoAoExcluirNotaInexistente() {
        when(notaFiscalRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> notaFiscalService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Nota Fiscal não encontrada para excluir");
    }
}
