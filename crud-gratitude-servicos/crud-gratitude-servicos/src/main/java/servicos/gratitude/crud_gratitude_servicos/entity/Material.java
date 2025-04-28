package servicos.gratitude.crud_gratitude_servicos.entity;


import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idMaterial;

    private String  nomeMaterial;

    private String  descricaoMaterial;

    private  Boolean  oculto;

    private String urlMaterial;

    @ManyToOne
    private Curso fkCurso;

    @OneToOne
    private Extensao extesao;

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public String setNomeMaterial() {
        this.nomeMaterial = nomeMaterial;
        return null;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    public String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }

    public Curso getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(Curso fkCurso) {
        this.fkCurso = fkCurso;
    }

    public Extensao getExtesao() {
        return extesao;
    }

    public void setExtesao(Extensao extesao) {
        this.extesao = extesao;
    }
}
