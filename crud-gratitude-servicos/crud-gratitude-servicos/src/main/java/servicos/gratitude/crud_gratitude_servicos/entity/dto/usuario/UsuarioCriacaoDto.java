package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UsuarioCriacaoDTO {

    @Size(min = 3,max = 10)
    @Schema(description = "Nome do Usuário",example = "John Doe")
    private String nome;

    @Email
    @Schema(description = "Email do usuario",example = "john@doe.com")
    private String email;

    @Size(min = 6,max = 20)
    @Schema(description = "Senha do usuario", example = "123456")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
