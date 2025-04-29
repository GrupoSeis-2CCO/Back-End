package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LocalDateTime data_entrada;
    private LocalDateTime ultimo_acesso;

    @ManyToOne
    private Cargo fk_cargo;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDateTime getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDateTime data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDateTime getUltimo_acesso() {
        return ultimo_acesso;
    }

    public void setUltimo_acesso(LocalDateTime ultimo_acesso) {
        this.ultimo_acesso = ultimo_acesso;
    }

    public Cargo getFk_cargo() {
        return fk_cargo;
    }

    public void setFk_cargo(Cargo fk_cargo) {
        this.fk_cargo = fk_cargo;
    }
}
