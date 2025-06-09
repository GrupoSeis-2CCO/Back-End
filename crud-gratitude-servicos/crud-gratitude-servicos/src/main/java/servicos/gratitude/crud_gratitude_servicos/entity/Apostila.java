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
    private Integer idApostila;

    private String nomeApostilaOriginal;
    private String nomeApostilaArmazenamento;
    private String descricaoApostila;
    private Integer tamanhoBytes;
    private LocalDateTime dataPostadoApostila;
    private LocalDateTime dataAtualizacaoApostila;
    private Boolean isApostilaOculto;
    private Integer ordemApostila;

    @ManyToOne(optional = false)
    private Curso fkCurso;
}
