package servicos.gratitude.crud_gratitude_servicos.entity.dto.material;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;

public class MaterialRequestDto {




    @NotBlank
    @Size(max = 50)
    private String  nomeMaterial;

    @NotBlank
    @Size(max = 100)
    private String  descricaoMaterial;

    @URL
    @NotBlank
    private String urlMaterial;



    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }
}
