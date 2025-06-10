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

    @Column(name = "id_video")
    private Integer idVideo;

    @Column(name = "nome_video")
    private String nomeVideo;

    @Column(name = "descricao_video")
    private String descricaoVideo;

    @Column(name = "url_video")
    private String urlVideo;

    @Column(name = "data_postado_video")
    private LocalDateTime dataPostadoVideo;

    @Column(name = "data_atualizado_video")
    private LocalDateTime dataAtualizacaoVideo;

    @Column(name = "ordem_video")
    private Integer ordemVideo;

    @Column(name = "is_video_oculto")
    private Boolean isVideoOculto;

    @ManyToOne
    @JoinColumn(name = "fk_curso", nullable = false)
    private Curso curso;
}
