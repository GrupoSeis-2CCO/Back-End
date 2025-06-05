package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;

import java.util.Objects;

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
    @JoinColumn(name = "avaliacao_id_avaliacao", referencedColumnName = "idAvaliacao", insertable = false, updatable = false)
    private Avaliacao avaliacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questao questao = (Questao) o;
        return Objects.equals(idQuestaoComposto, questao.idQuestaoComposto) && Objects.equals(enunciado, questao.enunciado) && Objects.equals(numeroQuestao, questao.numeroQuestao) && Objects.equals(avaliacao, questao.avaliacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestaoComposto, enunciado, numeroQuestao, avaliacao);
    }
}