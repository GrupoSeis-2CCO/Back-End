package servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TentativaRequestDto {

    @NotNull
    @Min(1)
    private Integer fkUsuario;

    @NotNull
    @Min(1)
    private Integer fkCurso;

    @NotNull
    @Min(1)
    private Integer fkAvaliacao;
}
