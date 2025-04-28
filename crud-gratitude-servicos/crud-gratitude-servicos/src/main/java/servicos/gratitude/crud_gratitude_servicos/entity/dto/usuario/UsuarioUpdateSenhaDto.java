package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateSenhaDto {

    @NotBlank
    @Size(max = 100)
    private String senha;

    public @NotBlank @Size(max = 100) String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank @Size(max = 100) String senha) {
        this.senha = senha;
    }
}
