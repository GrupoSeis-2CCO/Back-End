package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioListarDTO {

    @Schema(description = "Id do usuario", example = "1")
    private Integer idUsuario;

    @Schema(description = "Nome do usuario", example = "John Doe")
    private String nome;

    @Schema(description = "Email do usuario", example = "john@doe.com")
    private String email;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

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
}
