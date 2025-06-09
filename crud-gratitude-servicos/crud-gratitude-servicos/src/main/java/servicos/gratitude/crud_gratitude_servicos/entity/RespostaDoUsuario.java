package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.RespostaDoUsuarioCompoundKey;

@Entity
@Getter
@Setter
public class RespostaDoUsuario {
    @EmbeddedId
    private RespostaDoUsuarioCompoundKey respostaDoUsuarioCompoundKey;

    @ManyToOne(optional = false)
    @MapsId("idTentativaComposto")
    private Tentativa tentativa;

    @OneToOne(optional = false)
    @MapsId("idAlternativaComposto")
    private Alternativa alternativa;
}
