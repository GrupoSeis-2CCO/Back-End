package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;

import java.util.List;

public interface MaterialRepository  extends JpaRepository<Material, Integer> {
    List<Material> findByFkCurso(Curso fkCurso);
}
