package servicos.gratitude.crud_gratitude_servicos.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class UsuarioRequestDto {

    @NotBlank
    @NotNull
    @Max(100)
    private String nome;

    @CPF
    @NotNull
    private String cpf;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    @Max(11)
    private String cargo;

    public @NotBlank @NotNull @Max(100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull @Max(100) String nome) {
        this.nome = nome;
    }

    public @CPF @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF @NotNull String cpf) {
        this.cpf = cpf;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public @NotNull @NotBlank @Max(11) String getCargo() {
        return cargo;
    }

    public void setCargo(@NotNull @NotBlank @Max(11) String cargo) {
        this.cargo = cargo;
    }
}
