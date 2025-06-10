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
public class MaterialAlunoCompoundKey implements Serializable {


    private Integer idMaterialAluno;


    private MatriculaCompoundKey idMatriculaComposto;
}