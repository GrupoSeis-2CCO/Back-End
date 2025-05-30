package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestaoCompoundKey implements Serializable {
    private Integer idQuestao;
    private Integer fkAvaliacao;

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Integer getFkAvaliacao() {
        return fkAvaliacao;
    }

    public void setFkAvaliacao(Integer fkAvaliacao) {
        this.fkAvaliacao = fkAvaliacao;
    }
}
