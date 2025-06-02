package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class QuestaoCompoundKey implements Serializable {
    private Integer idQuestao;
    private Integer fkAvaliacao;
}
