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
    private TentativaCompoundKey idTentativaComposto;

    @Column(name = "dt_tentativa")
    private LocalDateTime dtTentativa;

    @ManyToOne(optional = false)
    @MapsId("idMatriculaComposto") // nome do campo em TentativaCompoundKey
    private Matricula matricula;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tentativa")
    private Avaliacao avaliacao;

    // Adicione manualmente se necessário
    public TentativaCompoundKey getIdTentativaComposto() {
        return idTentativaComposto;
    }
}