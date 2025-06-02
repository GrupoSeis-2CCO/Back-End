package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;


public class UsuarioRequestDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer idCargo;

    public @NotBlank @Size(min = 3, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, max = 100) String nome) {
        this.nome = nome;
    }

    public @CPF @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF @NotBlank String cpf) {
        this.cpf = cpf;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public @NotNull Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(@NotNull Integer idCargo) {
        this.idCargo = idCargo;
    }
}
