package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {

    @Id
    private Curso fkCurso;

    private Integer estrelas;
    private String motivo;

    @ManyToOne
    private Usuario fkUsuario;
}