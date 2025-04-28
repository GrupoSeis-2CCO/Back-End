package servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoRequestDto {

    @NotNull
    @Min(1)
    private Integer idCurso;

    public @NotNull @Min(1) Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(@NotNull @Min(1) Integer idCurso) {
        this.idCurso = idCurso;
    }
}
