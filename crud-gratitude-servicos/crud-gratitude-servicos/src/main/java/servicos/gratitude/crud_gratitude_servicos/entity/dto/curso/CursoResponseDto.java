package servicos.gratitude.crud_gratitude_servicos.entity.dto.curso;

import lombok.Data;

@Data
public class CursoResponseDto {
    private String tituloCurso;
    private String descricao;
    private String imagem;
    private Boolean ocultado;
    private Integer duracaoEstimada;
}
