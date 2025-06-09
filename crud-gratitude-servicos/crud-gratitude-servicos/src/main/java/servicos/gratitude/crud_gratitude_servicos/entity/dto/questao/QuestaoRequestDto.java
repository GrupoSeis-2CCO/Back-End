package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;

@Data
public class QuestaoRequestDto {
    private Integer fkAvaliacao;
    private String enunciado;
    private Integer numeroQuestao;
}