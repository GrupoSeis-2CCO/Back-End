package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.AlternativaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlternativaService {
    private final AlternativaRepository alternativaRepository;

    public Alternativa cadastrarAlternativa(Alternativa alternativa){
        return alternativaRepository.save(alternativa);
    }

    public List<Alternativa> listarAlternativasPorQuestao(Questao questao){
        return alternativaRepository.findAllByQuestao(questao);
    }

    public Optional<Alternativa> findById(AlternativaCompoundKey idAlternativaComposto){
        return alternativaRepository.findById(idAlternativaComposto);
    }

    public Alternativa atualizarAlternativa(Alternativa update){
        return alternativaRepository.save(update);
    }

    public void deletarAlternativa(AlternativaCompoundKey idAlternativaComposto){
        alternativaRepository.deleteById(idAlternativaComposto);
    }

    public Boolean existsById(AlternativaCompoundKey idAlternativaComposto){
        return alternativaRepository.existsById(idAlternativaComposto);
    }
}
