package servicos.gratitude.crud_gratitude_servicos.entity.dto.material;

import lombok.Data;

@Data
public class MaterialResponseDto {
    private String nomeMaterial;
    private String descricaoMaterial;
    private String urlMaterial;
    private String tituloCurso;
    private String extensao;
    private Boolean ocultado;
    private Integer ordem;
}