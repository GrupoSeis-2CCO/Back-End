package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;

import java.util.List;

public interface QuestaoRepository extends JpaRepository<Questao, Integer> {
    List<Questao> findAllByFkAvaliacao(Avaliacao fkAvaliacao);
}
