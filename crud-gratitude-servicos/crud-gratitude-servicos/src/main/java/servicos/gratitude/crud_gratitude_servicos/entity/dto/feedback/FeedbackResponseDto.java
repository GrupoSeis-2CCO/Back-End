package servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback;

import lombok.Data;

@Data
public class FeedbackResponseDto {
    private Integer idCurso;
    private String nomeCurso;
    private Integer estrelas;
    private String motivo;
}