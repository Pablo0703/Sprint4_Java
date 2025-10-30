package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Entity.PatioEntity;
import br.com.fiap.Challenge.Entity.ZonaPatioEntity;
import br.com.fiap.Challenge.Repository.PatioRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
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
class ZonaPatioServiceTest {

    @Mock
    private ZonaPatioRepository zonaPatioRepository;

    @Mock
    private PatioRepository patioRepository;

    @InjectMocks
    private ZonaPatioService zonaPatioService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarZonasPatio() {
        ZonaPatioEntity entity = new ZonaPatioEntity();
        entity.setId(1L);
        entity.setNomeZona("Zona A");
        entity.setTipoZona("Entrada");
        entity.setCapacidade(10);

        when(zonaPatioRepository.findAll()).thenReturn(List.of(entity));

        var lista = zonaPatioService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getNomeZona()).isEqualTo("Zona A");
        assertThat(lista.get(0).getTipoZona()).isEqualTo("Entrada");
    }

    // ✅ Caso 2 — buscar por ID existente
    @Test
    void deveBuscarZonaPorId() {
        ZonaPatioEntity entity = new ZonaPatioEntity();
        entity.setId(1L);
        entity.setNomeZona("Zona B");
        entity.setTipoZona("Saída");
        entity.setCapacidade(15);

        when(zonaPatioRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = zonaPatioService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getNomeZona()).isEqualTo("Zona B");
    }

    // ⚠️ Caso 3 — buscar por ID inexistente
    @Test
    void deveLancarExcecaoAoBuscarZonaInexistente() {
        when(zonaPatioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> zonaPatioService.buscarPorId(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Zona de Pátio não encontrada");
    }

    // ✅ Caso 4 — salvar zona de pátio com sucesso
    @Test
    void deveSalvarZonaPatio() {
        ZonaPatioDTO dto = new ZonaPatioDTO();
        dto.setId(1L);
        dto.setNomeZona("Zona C");
        dto.setTipoZona("Manobra");
        dto.setCapacidade(20);
        dto.setIdPatio(1L);

        PatioEntity patio = new PatioEntity();
        patio.setId(1L);
        patio.setNome("Pátio Central");

        ZonaPatioEntity entity = new ZonaPatioEntity();
        entity.setId(1L);
        entity.setNomeZona("Zona C");

        when(patioRepository.findById(1L)).thenReturn(Optional.of(patio));
        when(zonaPatioRepository.save(any(ZonaPatioEntity.class))).thenReturn(entity);

        zonaPatioService.salvar(dto);

        verify(zonaPatioRepository, times(1)).save(any(ZonaPatioEntity.class));
    }

    // ⚠️ Caso 5 — salvar zona com pátio inexistente
    @Test
    void deveLancarExcecaoAoSalvarZonaComPatioInexistente() {
        ZonaPatioDTO dto = new ZonaPatioDTO();
        dto.setId(1L);
        dto.setNomeZona("Zona D");
        dto.setIdPatio(99L);

        when(patioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> zonaPatioService.salvar(dto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Pátio não encontrado");
    }

    // ✅ Caso 6 — excluir zona existente
    @Test
    void deveExcluirZonaPatio() {
        when(zonaPatioRepository.existsById(1L)).thenReturn(true);

        zonaPatioService.excluir(1L);

        verify(zonaPatioRepository, times(1)).deleteById(1L);
    }

    // ⚠️ Caso 7 — tentar excluir inexistente
    @Test
    void deveLancarExcecaoAoExcluirZonaInexistente() {
        when(zonaPatioRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> zonaPatioService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Zona de Pátio não encontrada para exclusão");
    }
}
