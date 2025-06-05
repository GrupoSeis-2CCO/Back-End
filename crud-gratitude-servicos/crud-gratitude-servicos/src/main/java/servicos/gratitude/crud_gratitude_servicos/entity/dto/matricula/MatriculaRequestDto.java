package servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaRequestDto {
    private Integer fkUsuario;
    private Integer fkCurso;
}
