package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideo;

    private String nomeVideo;
    private String descricaoVideo;
    private String urlVideo;
    private LocalDateTime dataPostadoVideo;
    private LocalDateTime dataAtualizacaoVideo;
    private Integer ordemVideo;
    private Boolean isVideoOculto;

    @ManyToOne(optional = false)
    private Curso fkCurso;
}
