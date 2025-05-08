package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateOrdemDto {

    @NotNull
    @Min(1)
    private Integer ordem;

    public @NotNull @Min(1) Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(@NotNull @Min(1) Integer ordem) {
        this.ordem = ordem;
    }
}
