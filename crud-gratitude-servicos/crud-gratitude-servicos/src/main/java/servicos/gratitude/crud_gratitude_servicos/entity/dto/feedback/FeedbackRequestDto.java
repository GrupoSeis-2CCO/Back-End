package servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback;

import jakarta.validation.constraints.*;

public class FeedbackRequestDto {

    @NotNull
    private Integer idCurso;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer estrelas;

    @NotBlank
    @Size(max = 280)
    private String motivo;

    public @NotNull Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(@NotNull Integer idCurso) {
        this.idCurso = idCurso;
    }

    public @Min(1) @Max(5) @NotNull Integer getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(@Min(1) @Max(5) @NotNull Integer estrelas) {
        this.estrelas = estrelas;
    }

    public @NotBlank @Size(max = 280) String getMotivo() {
        return motivo;
    }

    public void setMotivo(@NotBlank @Size(max = 280) String motivo) {
        this.motivo = motivo;
    }
}
