package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula.MatriculaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula.MatriculaResponseDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MatriculaMapper {

    static public MatriculaCompoundKey toEntity(Integer fkUsuario, Integer fkCurso){
        MatriculaCompoundKey idMatriculaComposto = new MatriculaCompoundKey();

        idMatriculaComposto.setFkUsuario(fkUsuario);
        idMatriculaComposto.setFkCurso(fkCurso);

        return idMatriculaComposto;
    }

    static public Matricula toEntity(MatriculaRequestDto request,
                                     MatriculaCompoundKey idComposto,
                                     Usuario usuario,
                                     Curso curso
    ){
        Matricula matricula = new Matricula();

        matricula.setIdMatriculaComposto(idComposto);
        matricula.setDtInscricao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        matricula.setIsCompleto(false);
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);

        return matricula;
    }

    static public MatriculaResponseDto toEntity(Matricula matricula){
        MatriculaResponseDto response = new MatriculaResponseDto();

        response.setUsuario(matricula.getUsuario().getNome());
        response.setCurso(matricula.getCurso().getTituloCurso());
        response.setIncricao(matricula.getDtInscricao());
        response.setUltimoAcesso(matricula.getUltimoAcesso());
        response.setCompleto(matricula.getIsCompleto());
        response.setDataFinalizado(matricula.getDataFinalizacao());

        return response;
    }

    static public List<MatriculaResponseDto> toEntity(List<Matricula> matriculas){
        List<MatriculaResponseDto> responses = new ArrayList<>();

        for (Matricula matriculaDaVez : matriculas) {
            MatriculaResponseDto response = new MatriculaResponseDto();

            response.setUsuario(matriculaDaVez.getUsuario().getNome());
            response.setCurso(matriculaDaVez.getCurso().getTituloCurso());
            response.setIncricao(matriculaDaVez.getDtInscricao());
            response.setUltimoAcesso(matriculaDaVez.getUltimoAcesso());
            response.setCompleto(matriculaDaVez.getIsCompleto());
            response.setDataFinalizado(matriculaDaVez.getDataFinalizacao());

            responses.add(response);
        }

        return responses;
    }
}
