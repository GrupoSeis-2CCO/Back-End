package servicos.gratitude.crud_gratitude_servicos.entity.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialResponseDto;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {

    static public MaterialResponseDto toEntity(Material material){
        MaterialResponseDto response = new MaterialResponseDto();

        response.setDescricaoMaterial(material.getDescricao_material());
        response.setNomeMaterial(material.getNome_material());
        response.setUrlMaterial(material.getUrl_material());
        response.setTituloCurso(material.getFkCurso().getTitulo_curso());
        response.setExtensao(material.getExtesao().getTipo_extensao());

        return response;
    }



    static public List<MaterialResponseDto> toEntity(List<Material> materiais){
        List<MaterialResponseDto> responses = new ArrayList<>();

        for (Material materialdavez : materiais) {
            MaterialResponseDto response = new MaterialResponseDto();

            response.setExtensao(materialdavez.getExtesao().getTipo_extensao());
            response.setDescricaoMaterial(materialdavez.getDescricao_material());
            response.setNomeMaterial(materialdavez.setNomeMaterial());
            response.setUrlMaterial(materialdavez.getUrl_material());


            responses.add(response);
        }

        return responses;
    }


}
