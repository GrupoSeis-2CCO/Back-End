package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.RespostaDoUsuarioCompoundKey;

@Entity
@Getter
@Setter
public class RespostaDoUsuario {
    @EmbeddedId
    private RespostaDoUsuarioCompoundKey respostaDoUsuarioCompoundKey;



    @ManyToOne
    @JoinColumn(name = "FK_tentativa", insertable = false, updatable = false)
    private Tentativa tentativa;

    @ManyToOne
    @JoinColumn(name = "FK_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "FK_curso", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "FK_avaliacao", insertable = false, updatable = false)
    private Avaliacao avaliacao;

    @ManyToOne
    @JoinColumn(name = "FK_questao", insertable = false, updatable = false)
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "FK_alternativa", insertable = false, updatable = false)
    private Alternativa alternativa;
}
