package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestaoRequestDto {

    @NotNull
    @Min(1)
    private Integer fkAvaliacao;

    @NotBlank
    @Size(max = 100)
    private String enunciado;

    @NotNull
    @Min(1)
    private Integer numeroQuestao;

    public @NotNull @Min(1) Integer getFkAvaliacao() {
        return fkAvaliacao;
    }

    public void setFkAvaliacao(@NotNull @Min(1) Integer fkAvaliacao) {
        this.fkAvaliacao = fkAvaliacao;
    }

    public @NotBlank @Size(max = 100) String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(@NotBlank @Size(max = 100) String enunciado) {
        this.enunciado = enunciado;
    }

    public @NotNull @Min(1) Integer getNumeroQuestao() {
        return numeroQuestao;
    }

    public void setNumeroQuestao(@NotNull @Min(1) Integer numeroQuestao) {
        this.numeroQuestao = numeroQuestao;
    }
}
