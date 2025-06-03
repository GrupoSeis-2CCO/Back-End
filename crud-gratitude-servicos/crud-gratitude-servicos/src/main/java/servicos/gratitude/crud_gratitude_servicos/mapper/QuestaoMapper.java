package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoAtualizacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoResponseDto;

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
                QuestaoRequestDto request,
                Avaliacao avaliacao
    ){
        Questao questao = new Questao();

        questao.setIdQuestaoComposto(idQuestao);
        questao.setNumeroQuestao(request.getNumeroQuestao());
        questao.setEnunciado(request.getEnunciado());
        questao.setAvaliacao(avaliacao);

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

    static public Questao toEntity(Questao questaoDesatualizada, QuestaoCompoundKey idComposto, QuestaoAtualizacaoDto questaoParaAtualizar){
        Questao questao = new Questao();

        questao.setAvaliacao(questaoDesatualizada.getAvaliacao());
        questao.setEnunciado(questaoParaAtualizar.getEnunciado());
        questao.setNumeroQuestao(questaoParaAtualizar.getNumeroQuestao());
        questao.setIdQuestaoComposto(idComposto);

        return questao;
    }
}
