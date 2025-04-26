package servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ExtensaoUpdateDto {

    @NotBlank
    @URL
    private String icone;

    public @NotBlank @URL String getIcone() {
        return icone;
    }

    public void setIcone(@NotBlank @URL String icone) {
        this.icone = icone;
    }
}
