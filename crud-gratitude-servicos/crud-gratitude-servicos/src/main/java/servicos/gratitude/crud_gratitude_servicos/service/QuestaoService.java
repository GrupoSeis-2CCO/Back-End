package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.repository.QuestaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoService {

    private final QuestaoRepository questaoRepository;

    public QuestaoService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public Questao cadastrarQuestao(Questao questao){
        return questaoRepository.save(questao);
    }

    public List<Questao> listarQuestoesPorAvaliacao(Avaliacao fkAvaliacao){
        return questaoRepository.findAllByFkAvaliacao(fkAvaliacao);
    }

    public Optional<Questao> findById(Integer id){
        return questaoRepository.findById(id);
    }

    public Questao atualizarQuestao(Questao questao, Integer id){
        questao.setId_questao(id);
        return questaoRepository.save(questao);
    }

    public Boolean existsById(Integer id){
        return questaoRepository.existsById(id);
    }

    public void deletarQuestao(Integer id){
        questaoRepository.deleteById(id);
    }
}
