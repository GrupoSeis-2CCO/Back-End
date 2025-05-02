package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CursoMapper {

    static public Curso toEntity(CursoRequestDto request){
        Curso curso = new Curso();

        curso.setTitulo_curso(request.getTituloCurso());
        curso.setDescricao(request.getDescricao());
        curso.setImagem(request.getImagem());
        curso.setDuracao_estimada(request.getDuracaoEstimada());
        curso.setOcultado(true);

        return curso;
    }

    static public CursoResponseDto toEntity(Curso curso){
        CursoResponseDto response = new CursoResponseDto();

        response.setTituloCurso(curso.getTitulo_curso());
        response.setDescricao(curso.getDescricao());
        response.setImagem(curso.getImagem());
        response.setDuracaoEstimada(curso.getDuracao_estimada());
        response.setOcultado(curso.getOcultado());

        return response;
    }

    static public List<CursoResponseDto> toEntity(List<Curso> cursos){
        List<CursoResponseDto> responses = new ArrayList<>();

        for (Curso cursoDaVez : cursos) {
            CursoResponseDto response = new CursoResponseDto();

            response.setTituloCurso(cursoDaVez.getTitulo_curso());
            response.setDescricao(cursoDaVez.getDescricao());
            response.setImagem(cursoDaVez.getImagem());
            response.setDuracaoEstimada(cursoDaVez.getDuracao_estimada());
            response.setOcultado(cursoDaVez.getOcultado());

            responses.add(response);
        }

        return responses;
    }
}
