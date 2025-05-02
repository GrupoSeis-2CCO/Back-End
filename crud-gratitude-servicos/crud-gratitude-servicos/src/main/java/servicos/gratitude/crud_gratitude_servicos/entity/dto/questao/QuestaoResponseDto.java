package servicos.gratitude.crud_gratitude_servicos.entity.dto.questao;

public class QuestaoResponseDto {

    private Integer idAvaliacao;
    private Integer numeroQuestao;
    private String enunciado;
    private String resposta;

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getNumeroQuestao() {
        return numeroQuestao;
    }

    public void setNumeroQuestao(Integer numeroQuestao) {
        this.numeroQuestao = numeroQuestao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
