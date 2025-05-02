package servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CargoUpdateDto {

    @NotBlank(message = "O nome do cargo não pode estar em branco.")
    @Size(max = 50)
    private String nomeCargo;

    public @NotBlank @Size(max = 50) String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(@NotBlank @Size(max = 50) String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
