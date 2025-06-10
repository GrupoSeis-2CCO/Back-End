package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;

@Entity
@Getter
@Setter
public class Alternativa {

    @EmbeddedId
    private AlternativaCompoundKey alternativaChaveComposta;

    @Column(name = "texto")
    private String texto;

    @Column(name = "ordem_alternativa")
    private Integer ordem;

    @ManyToOne(optional = false)
    @MapsId("idQuestaoComposto")
    @JoinColumn(name = "FK_questao")
    private Questao questao;
}