package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.validation.constraints.*;

public class AlternativaRequestDto {

    @NotNull
    @Min(1)
    private Integer fkQuestao;

    @NotBlank
    @Size(max = 50)
    private String texto;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer ordem;

    public @NotNull @Min(1) Integer getFkQuestao() {
        return fkQuestao;
    }

    public void setFkQuestao(@NotNull @Min(1) Integer fkQuestao) {
        this.fkQuestao = fkQuestao;
    }

    public @NotBlank @Size(max = 50) String getTexto() {
        return texto;
    }

    public void setTexto(@NotBlank @Size(max = 50) String texto) {
        this.texto = texto;
    }

    public @NotNull @Min(1) @Max(4) Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(@NotNull @Min(1) @Max(4) Integer ordem) {
        this.ordem = ordem;
    }
}
