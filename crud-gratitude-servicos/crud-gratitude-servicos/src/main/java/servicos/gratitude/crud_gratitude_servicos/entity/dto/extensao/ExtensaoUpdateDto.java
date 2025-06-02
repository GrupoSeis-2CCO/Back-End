package servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ExtensaoUpdateDto {

    @URL
    @NotBlank
    private String icone;
}