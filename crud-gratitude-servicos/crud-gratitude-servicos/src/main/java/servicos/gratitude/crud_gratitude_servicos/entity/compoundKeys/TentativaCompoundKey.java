package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class TentativaCompoundKey implements Serializable {

    @Column(name = "FK_tentativa")  // Nome da coluna deve estar coerente com o DB
    private Integer idTentativa;

    @Embedded
    private MatriculaCompoundKey idMatriculaComposto;



}