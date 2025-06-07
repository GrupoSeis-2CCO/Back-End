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
    private AlternativaCompoundKey idAlternativaComposto;

    private String descricao;
    private boolean correta;

    @ManyToOne(optional = false)
    @MapsId("questaoKey")
    @JoinColumns({
        @JoinColumn(name = "questao_id_questao", referencedColumnName = "idQuestao"),
        @JoinColumn(name = "questao_fk_avaliacao", referencedColumnName = "fkAvaliacao")
    })
    private Questao questao;
} 