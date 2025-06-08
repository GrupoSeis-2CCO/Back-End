package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import java.util.List;

public interface ApostilaRepository extends JpaRepository<Apostila, Integer> {
    List<Apostila> findAllByFkCurso(Curso fkCurso);
}
