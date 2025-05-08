package servicos.gratitude.crud_gratitude_servicos.entity.dto.material;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MaterialUpdateDto {
    @NotBlank
    @Size(max = 50)
    private String  nomeMaterial;

    @NotBlank
    @Size(max = 100)
    private String  descricaoMaterial;

    @Min(1)
    @NotNull
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

    public @Min(1) @NotNull Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(@Min(1) @NotNull Integer ordem) {
        this.ordem = ordem;
    }
}
