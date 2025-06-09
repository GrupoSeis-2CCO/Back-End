package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa.TentativaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa.TentativaResponseDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TentativaMapper {

    static public TentativaCompoundKey toEntity(Integer idTentativa, MatriculaCompoundKey idMatriculaComposto){
        TentativaCompoundKey idTentativaComposto = new TentativaCompoundKey();

        idTentativaComposto.setIdTentativa(idTentativa);
        idTentativaComposto.setIdMatriculaComposto(idMatriculaComposto);

        return idTentativaComposto;
    }

    static public Tentativa toEntity(TentativaRequestDto request, TentativaCompoundKey idTentativaComposto, Matricula matricula, Avaliacao avaliacao){
        Tentativa tentativa = new Tentativa();

        tentativa.setIdTentativaComposto(idTentativaComposto);
        tentativa.setDtTentativa(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        tentativa.setMatricula(matricula);
        tentativa.setAvaliacao(avaliacao);

        return tentativa;
    }

    static public TentativaResponseDto toEntity(Tentativa tentativa){
        TentativaResponseDto response = new TentativaResponseDto();

        response.setNomeUsuario(tentativa.getMatricula().getUsuario().getNome());
        response.setNomeCurso(tentativa.getMatricula().getCurso().getTituloCurso());
        response.setDataTentativa(tentativa.getDtTentativa());

        return response;
    }

    static public List<TentativaResponseDto> toEntity (List<Tentativa> tentativas){
        List<TentativaResponseDto> responses = new ArrayList<>();

        for (Tentativa tentativaDaVez : tentativas) {
            TentativaResponseDto response = new TentativaResponseDto();

            response.setNomeUsuario(tentativaDaVez.getMatricula().getUsuario().getNome());
            response.setNomeCurso(tentativaDaVez.getMatricula().getCurso().getTituloCurso());
            response.setDataTentativa(tentativaDaVez.getDtTentativa());

            responses.add(response);
        }

        return responses;
    }
}