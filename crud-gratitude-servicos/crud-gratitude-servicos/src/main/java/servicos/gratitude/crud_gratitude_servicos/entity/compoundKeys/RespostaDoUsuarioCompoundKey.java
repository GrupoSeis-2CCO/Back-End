package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class RespostaDoUsuarioCompoundKey implements Serializable {



    @Column(name = "FK_usuario")
    private Integer fkUsuario;

    @Column(name = "FK_curso")
    private Integer fkCurso;

    @Column(name = "FK_tentativa")
    private Integer fkTentativa;

    @Column(name = "FK_avaliacao")
    private Integer fkAvaliacao;

    @Column(name = "FK_questao")
    private Integer fkQuestao;

    @Column(name = "FK_alternativa")
    private Integer fkAlternativa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RespostaDoUsuarioCompoundKey)) return false;
        RespostaDoUsuarioCompoundKey that = (RespostaDoUsuarioCompoundKey) o;
        return Objects.equals(fkUsuario, that.fkUsuario) &&
                Objects.equals(fkCurso, that.fkCurso) &&
                Objects.equals(fkTentativa, that.fkTentativa) &&
                Objects.equals(fkAvaliacao, that.fkAvaliacao) &&
                Objects.equals(fkQuestao, that.fkQuestao) &&
                Objects.equals(fkAlternativa, that.fkAlternativa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkUsuario, fkCurso, fkTentativa, fkAvaliacao, fkQuestao, fkAlternativa);
    }
}
