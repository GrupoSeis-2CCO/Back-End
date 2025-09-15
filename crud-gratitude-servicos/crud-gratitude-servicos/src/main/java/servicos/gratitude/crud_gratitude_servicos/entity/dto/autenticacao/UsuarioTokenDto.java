package servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao;

import lombok.Data;

@Data
public class UsuarioTokenDto {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String token;
    private Integer idCargo; // Adicione este campo
}