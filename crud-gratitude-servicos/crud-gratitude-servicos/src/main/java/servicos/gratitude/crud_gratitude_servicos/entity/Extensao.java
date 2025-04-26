package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Extensao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExtensao;

    private String tipoExtensao;
    private String icone;

    public Integer getIdExtensao() {
        return idExtensao;
    }

    public void setIdExtensao(Integer idExtensao) {
        this.idExtensao = idExtensao;
    }

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
