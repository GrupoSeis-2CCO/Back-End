package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Tentativa {

    @EmbeddedId
    private TentativaCompoundKey idTentativaComposto;

    private LocalDateTime dtTentativa;

    @ManyToOne(optional = false)
    @MapsId("idTentativaComposto")
    private Matricula matricula;

    @ManyToOne(optional = false)
    private Avaliacao avaliacao;
}
