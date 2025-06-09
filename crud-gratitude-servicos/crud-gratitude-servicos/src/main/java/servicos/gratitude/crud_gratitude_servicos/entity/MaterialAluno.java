package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MaterialAlunoCompoundKey;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MaterialAluno {

    @EmbeddedId
    private MaterialAlunoCompoundKey idMaterialAlunoComposto;

    private Boolean isFinalizado;
    private LocalDateTime ultimoAcesso;

    @ManyToOne
    private Video fkVideo;

    @ManyToOne
    private Apostila fkApostila;

    @ManyToOne(optional = false)
    @MapsId("idMatriculaComposto")
    private Matricula matricula;
}
