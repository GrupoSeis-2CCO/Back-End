package servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialAlunoRequestDto {

    @NotNull
    @Min(1)
    private Integer fkUsuario;

    @NotNull
    @Min(1)
    private Integer fkCurso;

    private Integer fkVideo;
    private Integer fkApostila;
}
