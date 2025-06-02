package servicos.gratitude.crud_gratitude_servicos.entity.dto.material;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;

@Data
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
}