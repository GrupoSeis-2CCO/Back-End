package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlternativaRequestDto {

    @NotNull
    @Min(1)
    private Integer fkQuestao;

    @NotNull
    @Min(1)
    private Integer fkAvaliacao;

    @NotBlank
    @Size(max = 50)
    private String texto;
}
