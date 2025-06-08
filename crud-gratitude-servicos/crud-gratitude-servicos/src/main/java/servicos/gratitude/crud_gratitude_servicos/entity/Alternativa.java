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

    private String texto;
    private Integer ordem;

    @ManyToOne(optional = false)
    @MapsId("questaoKey")
    private Questao questao;
}