package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Extensao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_extensao;

    private String tipo_extensao;
    private String icone;

    public Integer getId_extensao() {
        return id_extensao;
    }

    public void setId_extensao(Integer id_extensao) {
        this.id_extensao = id_extensao;
    }

    public String getTipo_extensao() {
        return tipo_extensao;
    }

    public void setTipo_extensao(String tipo_extensao) {
        this.tipo_extensao = tipo_extensao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
