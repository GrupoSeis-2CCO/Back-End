package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;

    @Id
    @OneToOne
    private Curso fkCurso;

    private Integer acertosMinimos;

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Curso getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(Curso fkCurso) {
        this.fkCurso = fkCurso;
    }

    public Integer getAcertosMinimos() {
        return acertosMinimos;
    }

    public void setAcertosMinimos(Integer acertosMinimos) {
        this.acertosMinimos = acertosMinimos;
    }
}
