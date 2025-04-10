package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
