package servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoRequestDto {

    @NotNull
    @Min(1)
    private Integer idCurso;
}