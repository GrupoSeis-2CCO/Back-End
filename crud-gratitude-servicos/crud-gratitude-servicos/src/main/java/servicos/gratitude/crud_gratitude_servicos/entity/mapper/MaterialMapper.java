package servicos.gratitude.crud_gratitude_servicos.entity.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialResponseDto;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {

    static public MaterialResponseDto toEntity(Material material){
        MaterialResponseDto response = new MaterialResponseDto();

        response.setDescricaoMaterial(material.getDescricaoMaterial());
        response.setNomeMaterial(material.getNomeMaterial());
        response.setUrlMaterial(material.getUrlMaterial());
        response.setTituloCurso(material.getFkCurso().getTituloCurso());
        response.setExtensao(material.getExtesao().getTipoExtensao());

        return response;
    }



    static public List<MaterialResponseDto> toEntity(List<Material> materiais){
        List<MaterialResponseDto> responses = new ArrayList<>();

        for (Material materialdavez : materiais) {
            MaterialResponseDto response = new MaterialResponseDto();

            response.setExtensao(materialdavez.getExtesao().getTipoExtensao());
            response.setDescricaoMaterial(materialdavez.getDescricaoMaterial());
            response.setNomeMaterial(materialdavez.setNomeMaterial());
            response.setUrlMaterial(materialdavez.getUrlMaterial());


            responses.add(response);
        }

        return responses;
    }


}
