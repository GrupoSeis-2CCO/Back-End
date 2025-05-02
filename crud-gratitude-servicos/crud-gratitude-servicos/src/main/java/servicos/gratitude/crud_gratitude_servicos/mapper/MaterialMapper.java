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

        material.setDescricao_material(request.getDescricaoMaterial());
        material.setUrl_material(request.getUrlMaterial());
        material.setNome_material(request.getNomeMaterial());
        material.setExtensao(extensao);
        material.setFkCurso(curso);
        material.setOculto(true);

        return material;
    }

    static public MaterialResponseDto toEntity(Material material){
        MaterialResponseDto response = new MaterialResponseDto();

        response.setDescricaoMaterial(material.getDescricao_material());
        response.setNomeMaterial(material.getNome_material());
        response.setUrlMaterial(material.getUrl_material());
        response.setTituloCurso(material.getFkCurso().getTitulo_curso());
        response.setExtensao(material.getExtensao().getTipo_extensao());
        response.setOcultado(material.getOculto());

        return response;
    }

    static public List<MaterialResponseDto> toEntity(List<Material> materiais){
        List<MaterialResponseDto> responses = new ArrayList<>();

        for (Material materialdavez : materiais) {
            MaterialResponseDto response = new MaterialResponseDto();

            response.setExtensao(materialdavez.getExtensao().getTipo_extensao());
            response.setDescricaoMaterial(materialdavez.getDescricao_material());
            response.setNomeMaterial(materialdavez.setNomeMaterial());
            response.setUrlMaterial(materialdavez.getUrl_material());


            responses.add(response);
        }

        return responses;
    }

    static public Material toEntity(MaterialUpdateDto update, Material material){
       Material materialAtualizado = new Material();

        materialAtualizado.setNome_material(update.getNomeMaterial());
        materialAtualizado.setDescricao_material(update.getDescricaoMaterial());

        materialAtualizado.setUrl_material(material.getUrl_material());
        materialAtualizado.setExtensao(material.getExtensao());
        materialAtualizado.setFkCurso(material.getFkCurso());
        materialAtualizado.setOculto(material.getOculto());

        return materialAtualizado;
    }
}
