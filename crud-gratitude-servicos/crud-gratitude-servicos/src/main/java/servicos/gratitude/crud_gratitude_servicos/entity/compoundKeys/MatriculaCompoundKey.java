package servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MatriculaCompoundKey implements Serializable {

    @JoinColumn(name = "FK_usuario")
    private Integer fkUsuario;

    @JoinColumn(name = "FK_curso")
    private Integer fkCurso;
}