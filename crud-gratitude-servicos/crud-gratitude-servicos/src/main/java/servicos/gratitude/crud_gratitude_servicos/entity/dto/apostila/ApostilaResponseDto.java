package servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApostilaResponseDto {
    private String curso;
    private String nome;
    private String nomeEmArmazenamento;
    private String descricao;
    private Integer tamanhoBytes;
    private LocalDateTime dataPostagem;
    private Boolean isOculto;
    private Integer ordem;
}
