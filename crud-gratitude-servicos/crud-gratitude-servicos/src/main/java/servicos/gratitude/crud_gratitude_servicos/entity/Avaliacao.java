package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_avaliacao;

    private Integer acertos_minimos;

    @OneToOne
    private Curso fk_curso;

    public Integer getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(Integer id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public Curso getFk_curso() {
        return fk_curso;
    }

    public void setFk_curso(Curso fk_curso) {
        this.fk_curso = fk_curso;
    }

    public Integer getAcertos_minimos() {
        return acertos_minimos;
    }

    public void setAcertos_minimos(Integer acertos_minimos) {
        this.acertos_minimos = acertos_minimos;
    }
}
