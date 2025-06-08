package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import lombok.Data;

@Data
public class AlternativaResponseDto {
    private String enunciadoQuestao;
    private String textoAlternativa;
    private Integer ordemAlternativa;
}
