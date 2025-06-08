package servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula;

import lombok.Data;

@Data
public class MatriculaRequestDto {
    private Integer fkUsuario;
    private Integer fkCurso;
}
