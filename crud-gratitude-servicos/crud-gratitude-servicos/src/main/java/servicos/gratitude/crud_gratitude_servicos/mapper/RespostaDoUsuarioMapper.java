package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.RespostaDoUsuario;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.RespostaDoUsuarioCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario.RespostaDoUsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario.RespostaDoUsuarioResponseDto;

import java.util.ArrayList;
import java.util.List;

public class RespostaDoUsuarioMapper {

    static public RespostaDoUsuarioCompoundKey toEntity(TentativaCompoundKey idTentativaComposto,
                                                        AlternativaCompoundKey idAlternativaComposto
    ){
        RespostaDoUsuarioCompoundKey idRespostaDoUsuarioComposto = new RespostaDoUsuarioCompoundKey();

        idRespostaDoUsuarioComposto.setIdTentativaComposto(idTentativaComposto);
        idRespostaDoUsuarioComposto.setIdAlternativaComposto(idAlternativaComposto);

        return idRespostaDoUsuarioComposto;
    }

    static public RespostaDoUsuario toEntity(RespostaDoUsuarioCompoundKey idRespostaDoUsuarioComposto,
                                             Tentativa tentativa,
                                             Alternativa alternativa
    ){
        RespostaDoUsuario respostaDoUsuario = new RespostaDoUsuario();

        respostaDoUsuario.setRespostaDoUsuarioCompoundKey(idRespostaDoUsuarioComposto);
        respostaDoUsuario.setTentativa(tentativa);
        respostaDoUsuario.setAlternativa(alternativa);

        return respostaDoUsuario;
    }

    static public RespostaDoUsuarioResponseDto toEntity(RespostaDoUsuario respostaDoUsuario){
        RespostaDoUsuarioResponseDto response = new RespostaDoUsuarioResponseDto();

        response.setUsuario(respostaDoUsuario.getTentativa().getMatricula().getUsuario().getNome());
        response.setCurso(respostaDoUsuario.getTentativa().getMatricula().getCurso().getTituloCurso());
        response.setQuestao(respostaDoUsuario.getAlternativa().getQuestao().getEnunciado());
        response.setAlternativa(respostaDoUsuario.getAlternativa().getTexto());

        return response;
    }

    static public List<RespostaDoUsuarioResponseDto> toEntity(List<RespostaDoUsuario> respostasDoUsuario) {
        List<RespostaDoUsuarioResponseDto> responses = new ArrayList<>();

        for (RespostaDoUsuario respostaDoUsuarioDaVez : respostasDoUsuario) {
            RespostaDoUsuarioResponseDto response = new RespostaDoUsuarioResponseDto();

            response.setUsuario(respostaDoUsuarioDaVez.getTentativa().getMatricula().getUsuario().getNome());
            response.setCurso(respostaDoUsuarioDaVez.getTentativa().getMatricula().getCurso().getTituloCurso());
            response.setQuestao(respostaDoUsuarioDaVez.getAlternativa().getQuestao().getEnunciado());
            response.setAlternativa(respostaDoUsuarioDaVez.getAlternativa().getTexto());

            responses.add(response);
        }

        return responses;
    }
}
