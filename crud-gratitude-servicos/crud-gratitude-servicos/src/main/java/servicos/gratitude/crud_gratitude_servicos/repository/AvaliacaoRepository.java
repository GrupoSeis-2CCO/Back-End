package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
