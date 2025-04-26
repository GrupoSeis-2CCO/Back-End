package servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao;

public class ExtensaoResponseDto {

    private String tipoExtensao;
    private String icone;

    public String getTipoExtensao() {
        return tipoExtensao;
    }

    public void setTipoExtensao(String tipoExtensao) {
        this.tipoExtensao = tipoExtensao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
