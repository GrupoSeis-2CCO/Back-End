package servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialAlunoResponseDto {
    private String usuario;
    private String curso;
    private String material;
    private String tipo;
    private Boolean finalizado;
    private LocalDateTime ultimoAcesso;
}
