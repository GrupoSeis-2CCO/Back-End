package servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RespostaDoUsuarioRequestDto {

    @NotNull
    @Min(1)
    private Integer fkUsuario;

    @NotNull
    @Min(1)
    private Integer fkCurso;

    @NotNull
    @Min(1)
    private Integer fkTentativa;

    @NotNull
    @Min(1)
    private Integer fkAvaliacao;

    @NotNull
    @Min(1)
    private Integer fkQuestao;

    @NotNull
    @Min(1)
    private Integer fkAlternativa;
}
