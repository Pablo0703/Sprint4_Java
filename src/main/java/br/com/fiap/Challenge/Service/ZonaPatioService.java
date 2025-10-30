package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Entity.PatioEntity;
import br.com.fiap.Challenge.Entity.ZonaPatioEntity;
import br.com.fiap.Challenge.Repository.PatioRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaPatioService {

    private final ZonaPatioRepository zonaPatioRepository;
    private final PatioRepository patioRepository;

    public ZonaPatioService(ZonaPatioRepository zonaPatioRepository, PatioRepository patioRepository) {
        this.zonaPatioRepository = zonaPatioRepository;
        this.patioRepository = patioRepository;
    }

    @Transactional(readOnly = true)
    public List<ZonaPatioDTO> listarTodos() {
        return zonaPatioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ZonaPatioDTO buscarPorId(Long id) {
        ZonaPatioEntity entity = zonaPatioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona de Pátio não encontrada com o ID: " + id));
        return toDTO(entity);
    }


    @Transactional
    public void salvar(ZonaPatioDTO dto) {
        // 1. Busca a entidade `PatioEntity` usando o ID fornecido no DTO.
        PatioEntity patio = patioRepository.findById(dto.getIdPatio())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado! ID: " + dto.getIdPatio()));

        // 2. Converte o DTO para uma entidade `ZonaPatioEntity`.
        ZonaPatioEntity entity = toEntity(dto);

        // 3. ✨ AQUI ESTÁ O PONTO-CHAVE: Associa a entidade Pátio completa.
        entity.setPatio(patio);

        // 4. Salva a entidade no banco de dados.
        zonaPatioRepository.save(entity);
    }


    @Transactional
    public void excluir(Long id) {
        if (!zonaPatioRepository.existsById(id)) {
            throw new EntityNotFoundException("Zona de Pátio não encontrada para exclusão. ID: " + id);
        }
        zonaPatioRepository.deleteById(id);
    }


    private ZonaPatioEntity toEntity(ZonaPatioDTO dto) {
        ZonaPatioEntity entity = new ZonaPatioEntity();
        entity.setId(dto.getId()); // Mantém o ID para casos de atualização
        entity.setNomeZona(dto.getNomeZona());
        entity.setTipoZona(dto.getTipoZona());
        entity.setCapacidade(dto.getCapacidade());
        // A associação do Pátio é feita no método `salvar`.
        return entity;
    }


    private ZonaPatioDTO toDTO(ZonaPatioEntity entity) {
        ZonaPatioDTO dto = new ZonaPatioDTO();
        dto.setId(entity.getId());
        dto.setNomeZona(entity.getNomeZona());
        dto.setTipoZona(entity.getTipoZona());
        dto.setCapacidade(entity.getCapacidade());

        // Define o ID do pátio e também o DTO do pátio para exibição
        if (entity.getPatio() != null) {
            dto.setIdPatio(entity.getPatio().getId());
            dto.setPatio(toPatioDTO(entity.getPatio()));
        }
        return dto;
    }


    private PatioDTO toPatioDTO(PatioEntity entity) {
        PatioDTO dto = new PatioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        // Mapeie outros campos se necessário
        return dto;
    }
}