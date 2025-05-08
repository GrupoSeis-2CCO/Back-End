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

    @NotNull
    @Min(1)
    private Integer fk_extensao;

    @NotNull
    @Min(1)
    private Integer fk_curso;

    @NotNull
    @Min(1)
    private Integer ordem;

    public @NotBlank @Size(max = 50) String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(@NotBlank @Size(max = 50) String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public @NotBlank @Size(max = 100) String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(@NotBlank @Size(max = 100) String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public @URL @NotBlank String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(@URL @NotBlank String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }

    public @NotNull @Min(1) Integer getFk_extensao() {
        return fk_extensao;
    }

    public void setFk_extensao(@NotNull @Min(1) Integer fk_extensao) {
        this.fk_extensao = fk_extensao;
    }

    public @NotNull @Min(1) Integer getFk_curso() {
        return fk_curso;
    }

    public void setFk_curso(@NotNull @Min(1) Integer fk_curso) {
        this.fk_curso = fk_curso;
    }

    public @NotNull @Min(1) Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(@NotNull @Min(1) Integer ordem) {
        this.ordem = ordem;
    }
}
