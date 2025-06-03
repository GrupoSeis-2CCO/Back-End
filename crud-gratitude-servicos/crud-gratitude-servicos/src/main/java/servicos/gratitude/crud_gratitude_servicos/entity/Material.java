package servicos.gratitude.crud_gratitude_servicos.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idMaterial;

    private String nomeMaterial;

    private String descricaoMaterial;

    private  Boolean  oculto;

    private String urlMaterial;

    private Integer ordemMaterial;

    @OneToOne
    private Extensao extensao;

    @ManyToOne
    private Curso fkCurso;
}