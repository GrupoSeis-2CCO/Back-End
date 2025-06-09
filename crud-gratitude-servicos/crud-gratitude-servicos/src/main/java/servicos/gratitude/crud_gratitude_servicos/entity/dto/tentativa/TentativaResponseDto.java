package servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TentativaResponseDto {
    private String nomeUsuario;
    private String nomeCurso;
    private LocalDateTime dataTentativa;
}
