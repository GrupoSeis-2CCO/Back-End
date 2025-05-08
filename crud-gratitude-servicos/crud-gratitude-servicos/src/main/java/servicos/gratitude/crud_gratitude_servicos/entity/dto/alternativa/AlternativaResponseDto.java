package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.persistence.criteria.CriteriaBuilder;

public class AlternativaResponseDto {

    private String enunciadoQuestao;
    private Integer ordem;
    private String texto;

    public String getEnunciadoQuestao() {
        return enunciadoQuestao;
    }

    public void setEnunciadoQuestao(String enunciadoQuestao) {
        this.enunciadoQuestao = enunciadoQuestao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
