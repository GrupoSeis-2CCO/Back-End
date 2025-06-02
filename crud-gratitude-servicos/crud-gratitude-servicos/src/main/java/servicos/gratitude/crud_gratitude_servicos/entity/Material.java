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
    private  Integer id_material;

    private String nome_material;

    private String descricao_material;

    private  Boolean  oculto;

    private String url_material;

    private Integer ordem_material;

    @OneToOne
    private Extensao extensao;

    @ManyToOne
    @JoinColumn(name = "fk_curso")
    private Curso fkCurso;
}