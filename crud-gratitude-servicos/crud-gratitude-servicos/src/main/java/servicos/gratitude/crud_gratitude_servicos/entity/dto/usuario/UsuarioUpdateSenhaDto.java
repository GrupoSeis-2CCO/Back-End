package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioUpdateSenhaDto {

    @NotBlank
    @Size(max = 100)
    private String senha;
}