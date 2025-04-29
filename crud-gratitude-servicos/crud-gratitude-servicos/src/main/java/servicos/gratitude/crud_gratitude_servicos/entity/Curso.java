package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    private String titulo_curso;
    private String descricao;
    private String imagem;
    private Boolean ocultado;
    private Integer duracao_estimada;

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getTitulo_curso() {
        return titulo_curso;
    }

    public void setTitulo_curso(String titulo_curso) {
        this.titulo_curso = titulo_curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Boolean getOcultado() {
        return ocultado;
    }

    public void setOcultado(Boolean ocultado) {
        this.ocultado = ocultado;
    }

    public Integer getDuracao_estimada() {
        return duracao_estimada;
    }

    public void setDuracao_estimada(Integer duracao_estimada) {
        this.duracao_estimada = duracao_estimada;
    }
}
