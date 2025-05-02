package servicos.gratitude.crud_gratitude_servicos.entity.dto.material;

import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoRequestTituloDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoResponseDto;

public class MaterialResponseDto {

    private String nomeMaterial;
    private String descricaoMaterial;
    private String urlMaterial;
    private String tituloCurso;
    private String extensao;
    private Boolean ocultado;

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }

    public String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public Boolean getOcultado() {
        return ocultado;
    }

    public void setOcultado(Boolean ocultado) {
        this.ocultado = ocultado;
    }
}
