package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.Entity.MotoEntity;
import br.com.fiap.Challenge.Entity.NotaFiscalEntity;
import br.com.fiap.Challenge.Entity.StatusMotoEntity;
import br.com.fiap.Challenge.Entity.TipoMotoEntity;
import br.com.fiap.Challenge.Repository.MotoRepository;
import br.com.fiap.Challenge.Repository.NotaFiscalRepository;
import br.com.fiap.Challenge.Repository.StatusMotoRepository;
import br.com.fiap.Challenge.Repository.TipoMotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MotoServiceTest {

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private TipoMotoRepository tipoMotoRepository;

    @Mock
    private StatusMotoRepository statusMotoRepository;

    @Mock
    private NotaFiscalRepository notaFiscalRepository;

    @InjectMocks
    private MotoService motoService;

    // ✅ Caso 1 — listar todos
    @Test
    void deveListarMotos() {
        MotoEntity entity = new MotoEntity();
        entity.setId(1L);
        entity.setPlaca("XYZ1234");

        when(motoRepository.findAll()).thenReturn(List.of(entity));

        var lista = motoService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getPlaca()).isEqualTo("XYZ1234");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarMotoPorId() {
        MotoEntity entity = new MotoEntity();
        entity.setId(1L);
        entity.setPlaca("ABC9876");

        when(motoRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = motoService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getPlaca()).isEqualTo("ABC9876");
    }

    // ✅ Caso 3 — salvar moto (sem duplicação)
    @Test
    void deveSalvarMotoComSucesso() {
        MotoDTO dto = new MotoDTO();
        dto.setId(1L);
        dto.setPlaca("ABC1234");
        dto.setModelo("CG 160");
        dto.setAnoFabricacao(2022);
        dto.setAnoModelo(2023);
        dto.setChassi("CHS123456789XYZ");
        dto.setCilindrada(160);
        dto.setCor("Preta");
        dto.setDataAquisicao(LocalDate.now());
        dto.setValorAquisicao(BigDecimal.valueOf(15000.00));
        dto.setTipo("Street");
        dto.setStatus("Disponível");
        dto.setNotaFiscalId(1L);

        MotoEntity entity = new MotoEntity();
        entity.setId(1L);
        entity.setPlaca("ABC1234");

        when(motoRepository.findByPlaca("ABC1234")).thenReturn(Optional.empty());
        when(motoRepository.findByChassi("CHS123456789XYZ")).thenReturn(Optional.empty());
        when(tipoMotoRepository.findByDescricao("Street")).thenReturn(Optional.of(new TipoMotoEntity()));
        when(statusMotoRepository.findByDescricao("Disponível")).thenReturn(Optional.of(new StatusMotoEntity()));
        when(notaFiscalRepository.findById(1L)).thenReturn(Optional.of(new NotaFiscalEntity()));
        when(motoRepository.save(any(MotoEntity.class))).thenReturn(entity);

        var salvo = motoService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getPlaca()).isEqualTo("ABC1234");
    }

    // ⚠️ Caso 4 — tentar salvar com placa duplicada
    @Test
    void deveLancarExcecaoAoSalvarComPlacaDuplicada() {
        MotoDTO dto = new MotoDTO();
        dto.setId(2L);
        dto.setPlaca("ABC1234");

        MotoEntity existente = new MotoEntity();
        existente.setId(1L);
        existente.setPlaca("ABC1234");

        when(motoRepository.findByPlaca("ABC1234")).thenReturn(Optional.of(existente));

        assertThatThrownBy(() -> motoService.salvar(dto))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("Placa 'ABC1234' já cadastrada!");
    }

    // ✅ Caso 5 — excluir moto
    @Test
    void deveExcluirMoto() {
        when(motoRepository.existsById(1L)).thenReturn(true);

        motoService.excluir(1L);

        verify(motoRepository, times(1)).deleteById(1L);
    }

    // ⚠️ Caso 6 — tentar excluir moto inexistente
    @Test
    void deveLancarExcecaoAoExcluirMotoInexistente() {
        when(motoRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> motoService.excluir(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Moto não encontrada para excluir");
    }
}
