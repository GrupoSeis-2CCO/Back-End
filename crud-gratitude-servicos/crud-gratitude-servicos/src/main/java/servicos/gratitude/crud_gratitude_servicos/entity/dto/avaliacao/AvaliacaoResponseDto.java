package servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao;

public class AvaliacaoResponseDto {
    private String nomeCurso;
    private Integer acertosMinimos;

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Integer getAcertosMinimos() {
        return acertosMinimos;
    }

    public void setAcertosMinimos(Integer acertosMinimos) {
        this.acertosMinimos = acertosMinimos;
    }
}
