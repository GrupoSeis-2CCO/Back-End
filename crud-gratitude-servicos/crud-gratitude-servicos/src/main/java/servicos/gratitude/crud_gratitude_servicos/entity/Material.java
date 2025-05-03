package servicos.gratitude.crud_gratitude_servicos.entity;


import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id_material;

    private String nome_material;

    private String descricao_material;

    private  Boolean  oculto;

    private String url_material;

    @ManyToOne
    @JoinColumn(name = "fk_curso")
    private Curso fkCurso;

    @OneToOne
    private Extensao extensao;

    public Integer getId_material() {
        return id_material;
    }

    public void setId_material(Integer id_material) {
        this.id_material = id_material;
    }

    public String getNome_material() {
        return nome_material;
    }

    public void setNome_material(String nome_material) {
        this.nome_material = nome_material;
    }

    public String getDescricao_material() {
        return descricao_material;
    }

    public void setDescricao_material(String descricao_material) {
        this.descricao_material = descricao_material;
    }

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    public String getUrl_material() {
        return url_material;
    }

    public void setUrl_material(String url_material) {
        this.url_material = url_material;
    }

    public Curso getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(Curso fkCurso) {
        this.fkCurso = fkCurso;
    }

    public Extensao getExtensao() {
        return extensao;
    }

    public void setExtensao(Extensao extensao) {
        this.extensao = extensao;
    }
}
