package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;

@Entity
@Getter
@Setter
public class Questao {
    @EmbeddedId
    private QuestaoCompoundKey idQuestaoComposto;

    private String enunciado;
    private Integer numeroQuestao;

    @ManyToOne(optional = false)
    @MapsId("fkAvaliacao")
    private Avaliacao avaliacao;
}