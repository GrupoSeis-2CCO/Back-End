package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;

@Entity
public class Questao {
    @EmbeddedId
    private QuestaoCompoundKey idQuestaoComposto;

    private String enunciado;
    private Integer numeroQuestao;

    @ManyToOne(optional = false)
    @MapsId("fkAvaliacao")
    @JoinColumn(referencedColumnName = "idAvaliacao", insertable = false, updatable = false)
    private Avaliacao avaliacao;

    public QuestaoCompoundKey getIdQuestaoComposto() {
        return idQuestaoComposto;
    }

    public void setIdQuestaoComposto(QuestaoCompoundKey idQuestaoComposto) {
        this.idQuestaoComposto = idQuestaoComposto;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Integer getNumeroQuestao() {
        return numeroQuestao;
    }

    public void setNumeroQuestao(Integer numeroQuestao) {
        this.numeroQuestao = numeroQuestao;
    }
}