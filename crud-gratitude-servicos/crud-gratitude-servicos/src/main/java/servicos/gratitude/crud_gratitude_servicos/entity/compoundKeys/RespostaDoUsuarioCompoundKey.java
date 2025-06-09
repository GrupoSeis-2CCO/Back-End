package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RespostaDoUsuarioCompoundKey {
    private TentativaCompoundKey idTentativaComposto;
    private AlternativaCompoundKey idAlternativaComposto;
}
