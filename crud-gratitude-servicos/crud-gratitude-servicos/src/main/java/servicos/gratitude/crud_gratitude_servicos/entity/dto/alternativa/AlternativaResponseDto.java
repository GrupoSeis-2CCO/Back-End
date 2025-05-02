package servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa;

import jakarta.persistence.criteria.CriteriaBuilder;

public class AlternativaResponseDto {

    private String enunciadoQuestao;
    private String letraAlternativa;
    private String texto;

    public String getLetraAlternativa() {
        return letraAlternativa;
    }

    public void setLetraAlternativa(String letraAlternativa) {
        this.letraAlternativa = letraAlternativa;
    }

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
}
