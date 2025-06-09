package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestaoRespostaDto {
    @NotNull
    @Min(1)
    private Integer fkAlternativa;
}
