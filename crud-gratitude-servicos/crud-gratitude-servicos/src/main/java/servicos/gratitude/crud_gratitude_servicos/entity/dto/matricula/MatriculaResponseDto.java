package servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatriculaResponseDto {
    private String usuario;
    private String curso;
    private LocalDateTime incricao;
    private LocalDateTime ultimoAcesso;
    private Boolean completo;
    private LocalDateTime dataFinalizado;
}
