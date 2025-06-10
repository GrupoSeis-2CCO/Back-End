package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "titulo_curso")
    private String tituloCurso;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "imagem")
    private String imagem;

    @Column(name = "ocultado")
    private Boolean ocultado;

    @Column(name = "duracao_estimada")
    private Integer duracaoEstimada;
}