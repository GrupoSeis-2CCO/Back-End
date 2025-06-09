package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class TentativaCompoundKey implements Serializable {

    @Column(name = "tentativa_id")
    private Integer idTentativa;

    private MatriculaCompoundKey matriculaId;
}