package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.LocalizacaoMotoDTO;
import br.com.fiap.Challenge.Entity.LocalizacaoMotoEntity;
import br.com.fiap.Challenge.Repository.LocalizacaoMotoRepository;
import br.com.fiap.Challenge.Repository.MotoRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LocalizacaoMotoServiceTest {

    @Mock
    private LocalizacaoMotoRepository localizacaoMotoRepository;

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private ZonaPatioRepository zonaPatioRepository;

    @InjectMocks
    private LocalizacaoMotoService localizacaoMotoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarLocalizacoes() {
        LocalizacaoMotoEntity entity = new LocalizacaoMotoEntity();
        entity.setId(1L);
        entity.setDataHoraEntrada(Date.from(LocalDateTime.now().minusHours(1)
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido
        entity.setDataHoraSaida(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido

        when(localizacaoMotoRepository.findAll()).thenReturn(List.of(entity));

        var lista = localizacaoMotoService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getId()).isEqualTo(1L);
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarLocalizacaoPorId() {
        LocalizacaoMotoEntity entity = new LocalizacaoMotoEntity();
        entity.setId(1L);
        entity.setDataHoraEntrada(Date.from(LocalDateTime.now().minusHours(2)
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido
        entity.setDataHoraSaida(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido

        when(localizacaoMotoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = localizacaoMotoService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
    }

    // ✅ Caso 3 — salvar localização
    @Test
    void deveSalvarLocalizacao() {
        LocalizacaoMotoDTO dto = new LocalizacaoMotoDTO();
        dto.setId(1L);
        dto.setDataHoraEntrada(Date.from(LocalDateTime.now().minusHours(1)
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido
        dto.setDataHoraSaida(Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault()).toInstant())); // ✅ corrigido
        dto.setIdMoto(1L);
        dto.setIdZona(1L);

        LocalizacaoMotoEntity entity = new LocalizacaoMotoEntity();
        entity.setId(1L);
        entity.setDataHoraEntrada(dto.getDataHoraEntrada());
        entity.setDataHoraSaida(dto.getDataHoraSaida());

        when(motoRepository.findById(1L))
                .thenReturn(Optional.of(new br.com.fiap.Challenge.Entity.MotoEntity()));
        when(zonaPatioRepository.findById(1L))
                .thenReturn(Optional.of(new br.com.fiap.Challenge.Entity.ZonaPatioEntity()));
        when(localizacaoMotoRepository.save(any(LocalizacaoMotoEntity.class))).thenReturn(entity);

        var salvo = localizacaoMotoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isEqualTo(1L);
    }

    // ✅ Caso 4 — excluir localização
    @Test
    void deveExcluirLocalizacao() {
        when(localizacaoMotoRepository.existsById(1L)).thenReturn(true);

        localizacaoMotoService.excluir(1L);

        verify(localizacaoMotoRepository, times(1)).deleteById(1L);
    }
}
