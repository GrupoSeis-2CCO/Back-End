package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Curso> {
    List<Feedback> findByFkCurso(Curso fkCurso);
}
