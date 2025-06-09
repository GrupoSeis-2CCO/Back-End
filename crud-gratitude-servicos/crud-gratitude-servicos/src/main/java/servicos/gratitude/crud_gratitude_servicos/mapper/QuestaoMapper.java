package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoAtualizacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRespostaDto;

import java.util.ArrayList;
import java.util.List;

public class QuestaoMapper {

    static public QuestaoCompoundKey toEntity(Integer idQuestao, Integer fkAvaliacao){
        QuestaoCompoundKey compoundKey = new QuestaoCompoundKey();

        compoundKey.setIdQuestao(idQuestao);
        compoundKey.setFkAvaliacao(fkAvaliacao);

        return compoundKey;
    }

    static public Questao toEntity (
            QuestaoCompoundKey idQuestao,
            Integer numeroQuestao,
            QuestaoRequestDto request,
            Avaliacao avaliacao
    ){
        Questao questao = new Questao();

        questao.setIdQuestaoComposto(idQuestao);
        questao.setNumeroQuestao(numeroQuestao);
        questao.setEnunciado(request.getEnunciado());
        questao.setAvaliacao(avaliacao);
        questao.setFkAlternativaCorreta(null);

        return questao;
    }

    static public QuestaoResponseDto toEntity(Questao questao){
        QuestaoResponseDto response = new QuestaoResponseDto();

        response.setEnunciado(questao.getEnunciado());
        response.setNumeroQuestao(questao.getNumeroQuestao());
        response.setIdAvaliacao(questao.getIdQuestaoComposto().getFkAvaliacao());

        return response;
    }

    static public List<QuestaoResponseDto> toEntity(List<Questao> questoes){
        List<QuestaoResponseDto> responses = new ArrayList<>();

        for (Questao questaoDaVez : questoes) {
        QuestaoResponseDto response = new QuestaoResponseDto();

        response.setEnunciado(questaoDaVez.getEnunciado());
        response.setNumeroQuestao(questaoDaVez.getNumeroQuestao());
        response.setIdAvaliacao(questaoDaVez.getIdQuestaoComposto().getFkAvaliacao());

        responses.add(response);
        }

        return responses;
    }

    static public Questao toEntity(Questao questaoDesatualizada, QuestaoCompoundKey idComposto, QuestaoAtualizacaoDto update){
        Questao questao = new Questao();

        questao.setEnunciado(update.getEnunciado());
        questao.setNumeroQuestao(update.getNumeroQuestao());

        questao.setIdQuestaoComposto(idComposto);
        questao.setAvaliacao(questaoDesatualizada.getAvaliacao());
        questao.setFkAlternativaCorreta(questaoDesatualizada.getFkAlternativaCorreta());

        return questao;
    }

    static public Questao toEntity(Questao questaoDesatualizada, QuestaoCompoundKey idComposto, Alternativa alternativaCorreta){
        Questao questao = new Questao();

        questao.setFkAlternativaCorreta(alternativaCorreta);

        questao.setAvaliacao(questaoDesatualizada.getAvaliacao());
        questao.setEnunciado(questaoDesatualizada.getEnunciado());
        questao.setNumeroQuestao(questaoDesatualizada.getNumeroQuestao());
        questao.setIdQuestaoComposto(idComposto);

        return questao;
    }
}
