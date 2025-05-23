package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

@Entity
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alternativa;

    @ManyToOne
    @JoinColumn(name = "fk_questao")
    private Questao fkQuestao;

    private String texto;

    private Integer ordem_alternativa;

    public Integer getId_alternativa() {
        return id_alternativa;
    }

    public void setId_alternativa(Integer id_alternativa) {
        this.id_alternativa = id_alternativa;
    }

    public Questao getFkQuestao() {
        return fkQuestao;
    }

    public void setFkQuestao(Questao fkQuestao) {
        this.fkQuestao = fkQuestao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getOrdem_alternativa() {
        return ordem_alternativa;
    }

    public void setOrdem_alternativa(Integer ordem_alternativa) {
        this.ordem_alternativa = ordem_alternativa;
    }
}
