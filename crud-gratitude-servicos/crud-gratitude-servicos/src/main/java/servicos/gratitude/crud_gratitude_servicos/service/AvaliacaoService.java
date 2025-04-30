package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.repository.AvaliacaoRepository;

import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao){
        return avaliacaoRepository.save(avaliacao);
    }

    public Boolean existsById(Integer id){
        return avaliacaoRepository.existsById(id);
    }

    public Optional<Avaliacao> findById(Integer id){
        return avaliacaoRepository.findById(id);
    }

    public Avaliacao atualizarAcertosMinimos(Avaliacao avaliacao, Integer id){
        avaliacao.setId_avaliacao(id);
        return avaliacaoRepository.save(avaliacao);
    }
}
