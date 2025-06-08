package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaUpdateDto;

import java.util.ArrayList;
import java.util.List;

public class AlternativaMapper {

    static public AlternativaCompoundKey toEntity(Integer idAlternativa, QuestaoCompoundKey idQuestaoComposto){
        AlternativaCompoundKey idAlternativaComposto = new AlternativaCompoundKey();

        idAlternativaComposto.setIdAlternativa(idAlternativa);
        idAlternativaComposto.setIdQuestaoComposto(idQuestaoComposto);

        return idAlternativaComposto;
    }

    static public Alternativa toEntity(AlternativaRequestDto request, AlternativaCompoundKey idComposto, Integer ordem, Questao questao){
        Alternativa alternativa = new Alternativa();

        alternativa.setAlternativaChaveComposta(idComposto);
        alternativa.setTexto(request.getTexto());
        alternativa.setOrdem(ordem);
        alternativa.setQuestao(questao);

        return alternativa;
    }

    static public AlternativaResponseDto toEntity(Alternativa alternativa){
        AlternativaResponseDto response = new AlternativaResponseDto();

        response.setEnunciadoQuestao(alternativa.getQuestao().getEnunciado());
        response.setTextoAlternativa(alternativa.getTexto());
        response.setOrdemAlternativa(alternativa.getOrdem());

        return response;
    }

    static public List<AlternativaResponseDto> toEntity(List<Alternativa> alternativas){
        List<AlternativaResponseDto> responses = new ArrayList<>();

        for (Alternativa alternativaDaVez : alternativas) {
        AlternativaResponseDto response = new AlternativaResponseDto();

        response.setEnunciadoQuestao(alternativaDaVez.getQuestao().getEnunciado());
        response.setTextoAlternativa(alternativaDaVez.getTexto());
        response.setOrdemAlternativa(alternativaDaVez.getOrdem());

        responses.add(response);
        }

        return responses;
    }

    static public Alternativa toEntity(Alternativa alternativa, AlternativaUpdateDto update){
        Alternativa alternativaAtualizada = new Alternativa();

        alternativaAtualizada.setTexto(update.getTexto());

        alternativaAtualizada.setAlternativaChaveComposta(alternativa.getAlternativaChaveComposta());
        alternativaAtualizada.setOrdem(alternativa.getOrdem());
        alternativaAtualizada.setQuestao(alternativa.getQuestao());

        return alternativaAtualizada;
    }
}
