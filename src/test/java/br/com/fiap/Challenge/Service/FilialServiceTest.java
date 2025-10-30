package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.FilialDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Entity.FilialEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import br.com.fiap.Challenge.Repository.FilialRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FilialServiceTest {

    @Mock
    private FilialRepository filialRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private FilialService filialService;

    // ✅ Caso 1 — listar filiais
    @Test
    void deveListarFiliais() {
        FilialEntity entity = new FilialEntity();
        entity.setId(1L);
        entity.setNome("Filial SP");

        when(filialRepository.findAll()).thenReturn(List.of(entity));

        var lista = filialService.listarTodos();

        assertThat(lista).isNotEmpty();
        assertThat(lista.get(0).getNome()).isEqualTo("Filial SP");
    }

    // ✅ Caso 2 — buscar por ID
    @Test
    void deveBuscarFilialPorId() {
        FilialEntity entity = new FilialEntity();
        entity.setId(1L);
        entity.setNome("Filial Rio");

        when(filialRepository.findById(1L)).thenReturn(Optional.of(entity));

        var dto = filialService.buscarPorId(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getNome()).isEqualTo("Filial Rio");
    }

    // ✅ Caso 3 — salvar filial
    @Test
    void deveSalvarFilial() {
        FilialDTO dto = new FilialDTO();
        dto.setNome("Filial Campinas");
        dto.setCnpj("12345678000190");

        FilialEntity entity = new FilialEntity();
        entity.setId(1L);
        entity.setNome("Filial Campinas");
        entity.setCnpj("12345678000190");

        when(filialRepository.save(any(FilialEntity.class))).thenReturn(entity);

        var salvo = filialService.salvar(dto);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Filial Campinas");
        assertThat(salvo.getCnpj()).isEqualTo("12345678000190");
    }

    // ✅ Caso 4 — excluir filial
    @Test
    void deveExcluirFilial() {
        when(filialRepository.existsById(1L)).thenReturn(true);

        filialService.excluir(1L);

        verify(filialRepository, times(1)).deleteById(1L);
    }
}