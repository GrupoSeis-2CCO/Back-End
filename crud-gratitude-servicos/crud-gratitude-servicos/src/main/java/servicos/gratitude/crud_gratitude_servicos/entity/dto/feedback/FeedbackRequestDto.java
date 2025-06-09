package servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FeedbackRequestDto {

    @NotNull
    private Integer idCurso;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer estrelas;

    @NotBlank
    @Size(max = 280)
    private String motivo;

    private Integer fkUsuario;
}