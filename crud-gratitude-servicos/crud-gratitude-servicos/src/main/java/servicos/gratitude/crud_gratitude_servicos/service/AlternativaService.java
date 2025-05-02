package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.repository.AlternativaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;

    public AlternativaService(AlternativaRepository alternativaRepository) {
        this.alternativaRepository = alternativaRepository;
    }

    public Alternativa cadastrarAlternativa(Alternativa alternativa){
        return alternativaRepository.save(alternativa);
    }

    public List<Alternativa> listarAlternativasPorQuestao(Questao fkQuestao){
        return alternativaRepository.findAllByFkQuestao(fkQuestao);
    }

    public Optional<Alternativa> findById(Integer id){
        return alternativaRepository.findById(id);
    }

    public Alternativa atualizarAlternativa(Alternativa alternativa, Integer id){
        alternativa.setId_alternativa(id);
        return alternativaRepository.save(alternativa);
    }

    public Boolean existsById(Integer id){
        return alternativaRepository.existsById(id);
    }

    public void deletarAlternativa(Integer id){
        alternativaRepository.deleteById(id);
    }
}
