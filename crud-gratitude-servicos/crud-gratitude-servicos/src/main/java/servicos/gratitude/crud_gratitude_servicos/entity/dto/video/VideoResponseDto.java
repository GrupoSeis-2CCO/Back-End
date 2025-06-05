package servicos.gratitude.crud_gratitude_servicos.entity.dto.video;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoResponseDto {

    private String curso;
    private String nome;
    private String descricao;
    private String url;
    private LocalDateTime dataPostagem;
    private Integer ordem;
    private Boolean isOculto;
}
