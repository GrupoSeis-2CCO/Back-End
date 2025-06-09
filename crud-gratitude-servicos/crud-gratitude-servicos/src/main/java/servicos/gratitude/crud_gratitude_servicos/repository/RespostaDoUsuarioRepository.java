package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.RespostaDoUsuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.RespostaDoUsuarioCompoundKey;

public interface RespostaDoUsuarioRepository extends JpaRepository<RespostaDoUsuario, RespostaDoUsuarioCompoundKey> {
}
