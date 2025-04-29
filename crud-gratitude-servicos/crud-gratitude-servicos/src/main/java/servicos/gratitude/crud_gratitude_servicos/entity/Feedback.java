package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Feedback {

    @Id
    private Curso fk_curso;

    private Integer estrelas;
    private String motivo;

    public Curso getFk_curso() {
        return fk_curso;
    }

    public void setFk_curso(Curso fk_curso) {
        this.fk_curso = fk_curso;
    }

    public Integer getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Integer estrelas) {
        this.estrelas = estrelas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
