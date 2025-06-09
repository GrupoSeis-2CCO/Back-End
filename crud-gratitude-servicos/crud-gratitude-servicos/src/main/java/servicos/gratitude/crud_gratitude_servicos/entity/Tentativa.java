package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Tentativa {

    @EmbeddedId
    private TentativaCompoundKey id;

    private LocalDateTime dtTentativa;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("matriculaId")
    private Matricula matricula;

    @ManyToOne(optional = false)
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;
}