package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
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


    @Column(name = "finalizada")
    private Boolean isFinalizado;

    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @ManyToOne
    @JoinColumn(name = "FK_video")
    private Video fkVideo;

    @ManyToOne
    @JoinColumn(name = "FK_apostila")
    private Apostila fkApostila;

    @ManyToOne(optional = false)
    @MapsId("idMatriculaComposto")
    private Matricula matricula;
}
