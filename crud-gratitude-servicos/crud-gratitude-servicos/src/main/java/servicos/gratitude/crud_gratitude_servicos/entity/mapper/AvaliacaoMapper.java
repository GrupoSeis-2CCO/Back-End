package servicos.gratitude.crud_gratitude_servicos.entity.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoAcertosDto;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoMapper {

    static public Avaliacao toEntity(Curso curso){
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setFkCurso(curso);
        avaliacao.setAcertosMinimos(0);

        return avaliacao;
    }

    static public AvaliacaoResponseDto toEntity(Avaliacao avaliacao){
        AvaliacaoResponseDto response = new AvaliacaoResponseDto();

        response.setNomeCurso(avaliacao.getFkCurso().getTituloCurso());
        response.setAcertosMinimos(avaliacao.getAcertosMinimos());

        return response;
    }

    static public List<AvaliacaoResponseDto> toEntity (List<Avaliacao> avaliacoes) {
        List<AvaliacaoResponseDto> responses = new ArrayList<>();

        for (Avaliacao avaliacaoDaVez : avaliacoes) {
            AvaliacaoResponseDto response = new AvaliacaoResponseDto();

            response.setNomeCurso(avaliacaoDaVez.getFkCurso().getTituloCurso());
            response.setAcertosMinimos(avaliacaoDaVez.getAcertosMinimos());

            responses.add(response);
        }

        return responses;
    }
}
