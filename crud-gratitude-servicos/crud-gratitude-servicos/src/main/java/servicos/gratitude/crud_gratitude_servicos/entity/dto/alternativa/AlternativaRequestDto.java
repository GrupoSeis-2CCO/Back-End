package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlternativaRequestDto {

    @NotNull
    @Min(1)
    private Integer fkQuestao;

    @NotBlank
    @Size(max = 50)
    private String texto;

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
}
