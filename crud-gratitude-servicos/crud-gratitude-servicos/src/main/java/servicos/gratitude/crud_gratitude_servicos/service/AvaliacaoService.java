package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao){
        return avaliacaoRepository.save(avaliacao);
    }
}
