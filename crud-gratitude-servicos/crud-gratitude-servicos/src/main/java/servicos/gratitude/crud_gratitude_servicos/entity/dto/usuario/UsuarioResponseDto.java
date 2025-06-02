package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioResponseDto {
    private String nome;
    private String cpf;
    private String email;
    private LocalDateTime dataEntrada;
    private LocalDateTime ultimoAcesso;
    private String cargo;
}