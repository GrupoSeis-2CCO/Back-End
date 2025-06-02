package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestaoRequestDto {

    @NotNull
    @Min(1)
    private Integer fkAvaliacao;

    @NotBlank
    @Size(max = 100)
    private String enunciado;

    @NotNull
    @Min(1)
    private Integer numeroQuestao;
}