package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Apostila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apostila")
    private Integer idApostila;

    @Column(name = "nome_apostila_original")
    private String nomeApostilaOriginal;

    @Column(name = "nome_apostila_armazenamento")
    private String nomeApostilaArmazenamento;

    @Column(name = "descricao_apostila")
    private String descricaoApostila;

    @Column(name = "tamanho_bytes")
    private Integer tamanhoBytes;

    @Column(name = "data_postado_apostila")
    private LocalDateTime dataPostadoApostila;

    @Column(name = "data_atualizacao_apostila")
    private LocalDateTime dataAtualizacaoApostila;

    @Column(name = "is_apostila_oculto")
    private Boolean isApostilaOculto;

    @Column(name = "ordem_apostila")
    private Integer ordemApostila;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_curso")
    private Curso fkCurso;
}
