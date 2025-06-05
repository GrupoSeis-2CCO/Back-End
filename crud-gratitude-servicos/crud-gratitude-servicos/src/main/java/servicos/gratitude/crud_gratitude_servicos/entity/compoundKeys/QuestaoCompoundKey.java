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
public class QuestaoCompoundKey implements Serializable {
    @Column(name = "id_questao")
    private Integer idQuestao;

    @Column(name = "fk_avaliacao")
    private Integer fkAvaliacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestaoCompoundKey that = (QuestaoCompoundKey) o;
        return Objects.equals(idQuestao, that.idQuestao) && Objects.equals(fkAvaliacao, that.fkAvaliacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestao, fkAvaliacao);
    }
}
