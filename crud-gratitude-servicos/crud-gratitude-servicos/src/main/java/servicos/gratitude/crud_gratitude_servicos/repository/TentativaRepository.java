package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;

import java.util.List;

public interface TentativaRepository extends JpaRepository<Tentativa, TentativaCompoundKey> {
    List<Tentativa> findAllByMatricula(Matricula matricula);
}
