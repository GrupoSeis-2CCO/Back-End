package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer idAvaliacao;

    @Column(name = "nota_minima")
    private Integer acertosMinimos;

    @OneToOne(optional = false)
    @JoinColumn(name = "FK_curso")
    private Curso fkCurso;
}
