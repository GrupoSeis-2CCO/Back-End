package servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApostilaUpdateDto {

    @NotBlank
    @Size(max = 100)
    private String nomeApostilaOriginal;

    @NotBlank
    @Size(max = 256)
    private String descricaoApostila;

    @NotNull
    @Min(1)
    private Integer ordemApostila;
}
