package servicos.gratitude.crud_gratitude_servicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Extensao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExtensao;

    private String tipoExtensao;
    private String icone;
}