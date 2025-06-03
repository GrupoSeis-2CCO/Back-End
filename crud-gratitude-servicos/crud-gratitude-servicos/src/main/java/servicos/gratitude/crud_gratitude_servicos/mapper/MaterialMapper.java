package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialUpdateDto;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {

    static public Material toEntity(MaterialRequestDto request, Curso curso, Extensao extensao){
        Material material = new Material();

        material.setDescricaoMaterial(request.getDescricaoMaterial());
        material.setUrlMaterial(request.getUrlMaterial());
        material.setNomeMaterial(request.getNomeMaterial());
        material.setExtensao(extensao);
        material.setFkCurso(curso);
        material.setOculto(true);

        return material;
    }

    static public MaterialResponseDto toEntity(Material material){
        MaterialResponseDto response = new MaterialResponseDto();

        response.setDescricaoMaterial(material.getDescricaoMaterial());
        response.setNomeMaterial(material.getNomeMaterial());
        response.setUrlMaterial(material.getUrlMaterial());
        response.setTituloCurso(material.getFkCurso().getTituloCurso());
        response.setExtensao(material.getExtensao().getTipoExtensao());
        response.setOcultado(material.getOculto());

        return response;
    }

    static public List<MaterialResponseDto> toEntity(List<Material> materiais){
        List<MaterialResponseDto> responses = new ArrayList<>();

        for (Material materialdavez : materiais) {
            MaterialResponseDto response = new MaterialResponseDto();

            response.setExtensao(materialdavez.getExtensao().getTipoExtensao());
            response.setDescricaoMaterial(materialdavez.getDescricaoMaterial());
            response.setNomeMaterial(materialdavez.getNomeMaterial());
            response.setUrlMaterial(materialdavez.getUrlMaterial());


            responses.add(response);
        }

        return responses;
    }

    static public Material toEntity(MaterialUpdateDto update, Material material){
       Material materialAtualizado = new Material();

        materialAtualizado.setNomeMaterial(update.getNomeMaterial());
        materialAtualizado.setDescricaoMaterial(update.getDescricaoMaterial());

        materialAtualizado.setUrlMaterial(material.getUrlMaterial());
        materialAtualizado.setExtensao(material.getExtensao());
        materialAtualizado.setFkCurso(material.getFkCurso());
        materialAtualizado.setOculto(material.getOculto());

        return materialAtualizado;
    }
}
