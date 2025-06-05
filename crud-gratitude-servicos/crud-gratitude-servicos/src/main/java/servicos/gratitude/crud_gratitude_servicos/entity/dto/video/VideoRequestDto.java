package servicos.gratitude.crud_gratitude_servicos.entity.dto.video;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class VideoRequestDto {

    @NotNull
    @Min(1)
    private Integer fkCurso;

    @NotBlank
    @Size(max = 100)
    private String nomeVideo;

    @NotBlank
    @Size(max = 256)
    private String descricaoVideo;

    @URL
    @NotBlank
    private String urlVideo;
}
