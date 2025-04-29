package servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoAcertosDto {

    @Min(1)
    @NotNull
    private Integer acertosMinimos;

    public @Min(1) @NotNull Integer getAcertosMinimos() {
        return acertosMinimos;
    }

    public void setAcertosMinimos(@Min(1) @NotNull Integer acertosMinimos) {
        this.acertosMinimos = acertosMinimos;
    }
}
