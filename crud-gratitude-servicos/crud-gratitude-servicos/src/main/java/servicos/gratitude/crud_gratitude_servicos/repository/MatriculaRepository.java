package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;

import java.util.List;

public interface MatriculaRepository extends JpaRepository <Matricula, MatriculaCompoundKey> {
    List<Matricula> findMatriculaByUsuario(Usuario usuario);
    List<Matricula> findMatriculaByCurso(Curso curso);
    List<Matricula> findMatriculaByIsCompleto(Boolean isFinalizada);
}