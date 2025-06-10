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


    @Column(name = "duracao_estimada")
    private LocalDateTime dtInscricao;

    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @Column(name = "completo")
    private Boolean isCompleto;


    @Column(name = "data_finalizado")
    private LocalDateTime dataFinalizacao;

    @ManyToOne(optional = false)
    @MapsId("fkUsuario")
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @MapsId("fkCurso")
    @JoinColumn(name = "fk_curso", referencedColumnName = "id_curso")
    private Curso curso;
}
