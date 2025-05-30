package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;

    private Integer acertosMinimos;

    @OneToOne
    private Curso fk_curso;

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Curso getFk_curso() {
        return fk_curso;
    }

    public void setFk_curso(Curso fk_curso) {
        this.fk_curso = fk_curso;
    }

    public Integer getAcertosMinimos() {
        return acertosMinimos;
    }

    public void setAcertosMinimos(Integer acertosMinimos) {
        this.acertosMinimos = acertosMinimos;
    }
}
