package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.HistoricoLocalizacaoDTO;
import br.com.fiap.Challenge.Entity.HistoricoLocalizacaoEntity;
import br.com.fiap.Challenge.Repository.HistoricoLocalizacaoRepository;
import br.com.fiap.Challenge.Repository.MotoRepository;
import br.com.fiap.Challenge.Repository.MotociclistaRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
import br.com.fiap.Challenge.Repository.StatusOperacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class HistoricoLocalizacaoServiceTest {

    @Mock
    private HistoricoLocalizacaoRepository historicoLocalizacaoRepository;

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private MotociclistaRepository motociclistaRepository;

    @Mock
    private ZonaPatioRepository zonaPatioRepository;

    @Mock
    private StatusOperacaoRepository statusOperacaoRepository;

    @InjectMocks
    private HistoricoLocalizacaoService historicoLocalizacaoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarHistoricos() {
        HistoricoLocalizacaoEntity entity = new HistoricoLocalizacaoEntity();
        entity.setId(1L);
        entity.setKmRodados(BigDecimal.valueOf(120.5)); // ✅ corrigido

        when(historicoLocalizacaoRepository.findAll()).thenReturn(List.of(entity));

        var lista = historicoLocalizacaoService.listarTodos();
        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getKmRodados()).isEqualTo(BigDecimal.valueOf(120.5)); // ✅ corrigido
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarHistoricoPorId() {
        HistoricoLocalizacaoEntity entity = new HistoricoLocalizacaoEntity();
        entity.setId(1L);
        entity.setKmRodados(BigDecimal.valueOf(90.0)); // ✅ corrigido

        when(historicoLocalizacaoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = historicoLocalizacaoService.buscarPorId(1L);
        assertThat(dto).isNotNull();
        assertThat(dto.getKmRodados()).isEqualTo(BigDecimal.valueOf(90.0)); // ✅ corrigido
    }

    // ✅ Caso 3 — salvar histórico
    @Test
    void deveSalvarHistorico() {
        HistoricoLocalizacaoDTO dto = new HistoricoLocalizacaoDTO();
        dto.setId(1L);
        dto.setKmRodados(BigDecimal.valueOf(150.0)); // ✅ corrigido
        dto.setDataHoraEntrada(LocalDateTime.now());
        dto.setDataHoraSaida(LocalDateTime.now().plusHours(2));
        dto.setMotoId(1L);

        HistoricoLocalizacaoEntity entity = new HistoricoLocalizacaoEntity();
        entity.setId(1L);
        entity.setKmRodados(BigDecimal.valueOf(150.0)); // ✅ corrigido

        when(motoRepository.findById(1L)).thenReturn(Optional.of(new br.com.fiap.Challenge.Entity.MotoEntity())); // ✅ mock válido
        when(historicoLocalizacaoRepository.save(any(HistoricoLocalizacaoEntity.class))).thenReturn(entity);

        var salvo = historicoLocalizacaoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getKmRodados()).isEqualTo(BigDecimal.valueOf(150.0)); // ✅ corrigido
    }

    // ✅ Caso 4 — excluir histórico
    @Test
    void deveExcluirHistorico() {
        when(historicoLocalizacaoRepository.existsById(1L)).thenReturn(true);

        historicoLocalizacaoService.excluir(1L);

        verify(historicoLocalizacaoRepository, times(1)).deleteById(1L);
    }
}
