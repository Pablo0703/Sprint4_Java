package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.Entity.FilialEntity;
import br.com.fiap.Challenge.Entity.PatioEntity;
import br.com.fiap.Challenge.Repository.FilialRepository;
import br.com.fiap.Challenge.Repository.PatioRepository;
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
class PatioServiceTest {

    @Mock
    private PatioRepository patioRepository;

    @Mock
    private FilialRepository filialRepository;

    @InjectMocks
    private PatioService patioService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarPatios() {
        PatioEntity entity = new PatioEntity();
        entity.setId(1L);
        entity.setNome("Pátio Central");
        entity.setAreaM2(250.0);  // ✅ era BigDecimal
        entity.setCapacidade(30);
        entity.setAtivo("S");     // ✅ era boolean

        when(patioRepository.findAll()).thenReturn(List.of(entity));

        var lista = patioService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getNome()).isEqualTo("Pátio Central");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarPatioPorId() {
        PatioEntity entity = new PatioEntity();
        entity.setId(1L);
        entity.setNome("Pátio Norte");

        when(patioRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = patioService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getNome()).isEqualTo("Pátio Norte");
    }

    // ✅ Caso 3 — salvar pátio
    @Test
    void deveSalvarPatio() {
        PatioDTO dto = new PatioDTO();
        dto.setId(1L);
        dto.setNome("Pátio Leste");
        dto.setAreaM2(500.0);  // ✅ era BigDecimal
        dto.setCapacidade(40);
        dto.setAtivo("S");     // ✅ era boolean
        dto.setIdFilial(1L);

        PatioEntity entity = new PatioEntity();
        entity.setId(1L);
        entity.setNome("Pátio Leste");

        when(filialRepository.findById(1L)).thenReturn(Optional.of(new FilialEntity()));
        when(patioRepository.save(any(PatioEntity.class))).thenReturn(entity);

        var salvo = patioService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Pátio Leste");
    }

    // ⚠️ Caso 4 — salvar pátio com filial inexistente
    @Test
    void deveLancarExcecaoAoSalvarComFilialInexistente() {
        PatioDTO dto = new PatioDTO();
        dto.setId(1L);
        dto.setNome("Pátio Sul");
        dto.setIdFilial(99L);

        when(filialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> patioService.salvar(dto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Filial não encontrada");
    }

    // ✅ Caso 5 — excluir pátio
    @Test
    void deveExcluirPatio() {
        when(patioRepository.existsById(1L)).thenReturn(true);

        patioService.excluir(1L);

        verify(patioRepository, times(1)).deleteById(1L);
    }

    // ⚠️ Caso 6 — tentar excluir pátio inexistente
    @Test
    void deveLancarExcecaoAoExcluirPatioInexistente() {
        when(patioRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> patioService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Pátio não encontrado para exclusão");
    }
}
