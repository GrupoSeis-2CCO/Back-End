package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Matricula {

    @EmbeddedId
    private MatriculaCompoundKey idMatriculaComposto;

    private LocalDateTime dtInscricao;
    private LocalDateTime ultimoAcesso;
    private Boolean isCompleto;
    private LocalDateTime dataFinalizacao;

    @ManyToOne(optional = false)
    @MapsId("fkUsuario")
    @JoinColumn(name = "fk_usuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("fkCurso")
    @JoinColumn(name = "fk_curso", referencedColumnName = "idCurso", insertable = false, updatable = false)
    private Curso curso;
}
