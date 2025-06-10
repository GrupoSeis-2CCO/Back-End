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


    @Column(name = "enunciado")
    private String enunciado;


    @Column(name = "numero_questao")
    private Integer numeroQuestao;

    @ManyToOne(optional = false)
    @MapsId("fkAvaliacao")
    @JoinColumn(name = "fk_avaliacao")
    private Avaliacao avaliacao;

    @OneToOne
    @JoinColumn(name = "fk_alternativa_correta")
    private Alternativa fkAlternativaCorreta;
}