package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

import lombok.Data;

@Data
public class QuestaoResponseDto {
    private Integer idAvaliacao;
    private Integer numeroQuestao;
    private String enunciado;
    private String resposta;
}