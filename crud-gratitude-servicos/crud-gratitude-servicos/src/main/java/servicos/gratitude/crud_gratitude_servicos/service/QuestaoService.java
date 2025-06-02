package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.QuestaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestaoService {
    private final QuestaoRepository questaoRepository;
    
    public Questao cadastrarQuestao(Questao questao){
        return questaoRepository.save(questao);
    }

    public List<Questao> listarQuestoesPorAvaliacao(Avaliacao fkAvaliacao){
        return questaoRepository.findAllByAvaliacao(fkAvaliacao);
    }

    public Optional<Questao> findById(QuestaoCompoundKey id){
        return questaoRepository.findById(id);
    }

    public Boolean existsById(QuestaoCompoundKey id){
        return questaoRepository.existsById(id);
    }

    public void deletarQuestao(QuestaoCompoundKey id){
        questaoRepository.deleteById(id);
    }
}
