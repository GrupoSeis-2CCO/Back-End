package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;

import java.util.List;

public interface AlternativaRepository extends JpaRepository<Alternativa, Integer> {
    List<Alternativa> findAllByFkQuestao(Questao fkQuestao);
}
