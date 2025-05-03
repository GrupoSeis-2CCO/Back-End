package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;

@Entity
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_questao;


    @ManyToOne
    @JoinColumn(name = "fk_avaliacao")
    private Avaliacao fkAvaliacao;

    private String enunciado;
    private Integer numero_questao;

    @OneToOne
    private Alternativa fk_resposta;

    public Integer getId_questao() {
        return id_questao;
    }

    public void setId_questao(Integer id_questao) {
        this.id_questao = id_questao;
    }

    public Avaliacao getFkAvaliacao() {
        return fkAvaliacao;
    }

    public void setFkAvaliacao(Avaliacao fkAvaliacao) {
        this.fkAvaliacao = fkAvaliacao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Integer getNumero_questao() {
        return numero_questao;
    }

    public void setNumero_questao(Integer numero_questao) {
        this.numero_questao = numero_questao;
    }

    public Alternativa getFk_resposta() {
        return fk_resposta;
    }

    public void setFk_resposta(Alternativa fk_resposta) {
        this.fk_resposta = fk_resposta;
    }
}
