package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_cargo")
    private Cargo fkCargo;
}