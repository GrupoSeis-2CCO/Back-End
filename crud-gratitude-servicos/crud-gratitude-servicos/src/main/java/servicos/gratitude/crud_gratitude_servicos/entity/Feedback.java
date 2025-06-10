package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {

    @Id

    @Column(name = "FK_curso")
    private Curso fkCurso;

    @Column(name = "estrelas")
    private Integer estrelas;

    @Column(name = "motivo")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "FK_usuario")
    private Usuario fkUsuario;
}