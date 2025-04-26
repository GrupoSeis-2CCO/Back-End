package servicos.gratitude.crud_gratitude_servicos.entity.dto.curso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

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

    public @NotBlank @Size(max = 50) String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(@NotBlank @Size(max = 50) String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public @NotBlank @Size(max = 100) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @Size(max = 100) String descricao) {
        this.descricao = descricao;
    }

    public @URL @NotBlank String getImagem() {
        return imagem;
    }

    public void setImagem(@URL @NotBlank String imagem) {
        this.imagem = imagem;
    }

    public @Min(1) @NotNull Integer getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(@Min(1) @NotNull Integer duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }
}
