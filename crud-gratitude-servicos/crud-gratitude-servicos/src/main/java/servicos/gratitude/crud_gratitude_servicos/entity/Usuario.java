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
    private Integer idUsuario;

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LocalDateTime data_entrada;
    private LocalDateTime ultimo_acesso;

    @ManyToOne
    private Cargo fk_cargo;
}