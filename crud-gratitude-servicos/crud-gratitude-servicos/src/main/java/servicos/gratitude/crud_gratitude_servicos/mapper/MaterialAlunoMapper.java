package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.MaterialAluno;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Video;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MaterialAlunoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno.MaterialAlunoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno.MaterialAlunoResponseDto;

import java.util.ArrayList;
import java.util.List;

public class MaterialAlunoMapper {

    static public MaterialAlunoCompoundKey toEntity(Integer idMaterialAluno, MatriculaCompoundKey idMatriculaComposto){
        MaterialAlunoCompoundKey idMaterialAlunoComposto = new MaterialAlunoCompoundKey();

        idMaterialAlunoComposto.setIdMaterialAluno(idMaterialAluno);
        idMaterialAlunoComposto.setIdMatriculaComposto(idMatriculaComposto);

        return idMaterialAlunoComposto;
    }

    static public MaterialAluno toEntity (MaterialAlunoRequestDto request, MaterialAlunoCompoundKey idMaterialAlunoComposto, Matricula matricula, Video video, Apostila apostila){
        MaterialAluno materialAluno = new MaterialAluno();

        materialAluno.setIdMaterialAlunoComposto(idMaterialAlunoComposto);
        materialAluno.setIsFinalizado(false);
        materialAluno.setUltimoAcesso(null);
        materialAluno.setMatricula(matricula);
        materialAluno.setFkVideo(video);
        materialAluno.setFkApostila(apostila);

        return materialAluno;
    }

    static public MaterialAlunoResponseDto toEntity(MaterialAluno materialAluno){
        MaterialAlunoResponseDto response = new MaterialAlunoResponseDto();

        response.setUsuario(materialAluno.getMatricula().getUsuario().getNome());
        response.setCurso(materialAluno.getMatricula().getCurso().getTituloCurso());
        response.setFinalizado(materialAluno.getIsFinalizado());
        response.setUltimoAcesso(materialAluno.getUltimoAcesso());

        if (materialAluno.getFkVideo() != null){
            response.setMaterial(materialAluno.getFkVideo().getNomeVideo());
            response.setTipo("Vídeo");
        } else {
            response.setMaterial(materialAluno.getFkApostila().getNomeApostilaOriginal());
            response.setTipo("Apostila");
        }

        return response;
    }

    static public List<MaterialAlunoResponseDto> toEntity(List<MaterialAluno> materiaisAluno){
        List<MaterialAlunoResponseDto> responses = new ArrayList<>();

        for (MaterialAluno materialAlunoDaVez : materiaisAluno) {
        MaterialAlunoResponseDto response = new MaterialAlunoResponseDto();

        response.setUsuario(materialAlunoDaVez.getMatricula().getUsuario().getNome());
        response.setCurso(materialAlunoDaVez.getMatricula().getCurso().getTituloCurso());
        response.setFinalizado(materialAlunoDaVez.getIsFinalizado());
        response.setUltimoAcesso(materialAlunoDaVez.getUltimoAcesso());

        if (materialAlunoDaVez.getFkVideo() != null){
            response.setMaterial(materialAlunoDaVez.getFkVideo().getNomeVideo());
            response.setTipo("Vídeo");
        } else {
            response.setMaterial(materialAlunoDaVez.getFkApostila().getNomeApostilaOriginal());
            response.setTipo("Apostila");
        }

            responses.add(response);
        }
        return responses;
    }
}
