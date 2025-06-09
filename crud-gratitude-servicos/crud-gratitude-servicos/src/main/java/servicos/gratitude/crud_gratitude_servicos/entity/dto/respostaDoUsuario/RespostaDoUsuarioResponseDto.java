package servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario;

import lombok.Data;

@Data
public class RespostaDoUsuarioResponseDto {
    private String usuario;
    private String curso;
    private String questao;
    private String alternativa;
}
