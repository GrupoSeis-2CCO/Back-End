package servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialAlunoUpdateDto {

    @NotNull
    private LocalDateTime ultimoAcessoNovo;
}
