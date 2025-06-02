package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {

    @Id
    @Column(name = "fk_curso")
    private Curso fkCurso;

    private Integer estrelas;
    private String motivo;
}