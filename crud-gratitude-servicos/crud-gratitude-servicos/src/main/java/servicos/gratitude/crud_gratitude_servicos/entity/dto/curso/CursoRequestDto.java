package servicos.gratitude.crud_gratitude_servicos.entity.dto.curso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CursoRequestDto {

    @NotBlank
    @Size(max = 50)
    private String tituloCurso;

    @NotBlank
    @Size(max = 100)
    private String descricao;

    @URL
    @NotBlank
    private String imagem;

    @Min(1)
    @NotNull
    private Integer duracaoEstimada;
}