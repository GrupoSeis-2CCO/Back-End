package servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatriculaResponseDto {
    private String usuario;
    private String curso;
    private LocalDateTime incricao;
    private LocalDateTime ultimoAcesso;
    private Boolean completo;
    private LocalDateTime dataFinalizado;
}
