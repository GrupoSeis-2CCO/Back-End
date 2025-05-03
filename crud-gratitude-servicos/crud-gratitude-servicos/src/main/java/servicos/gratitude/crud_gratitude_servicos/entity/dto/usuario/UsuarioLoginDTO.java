package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioLoginDTO {

    @Schema(description = "Email do usuario", example = "john@doe.com")
    private String email;

    @Schema(description = "Senha do usuario", example = "123456")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
