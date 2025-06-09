package servicos.gratitude.crud_gratitude_servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicos.gratitude.crud_gratitude_servicos.entity.MaterialAluno;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MaterialAlunoCompoundKey;

import java.util.List;

public interface MaterialAlunoRepository extends JpaRepository<MaterialAluno, MaterialAlunoCompoundKey> {
    List<MaterialAluno> findAllByMatricula(Matricula matricula);
    List<MaterialAluno> findAllByMatriculaAndIsFinalizado(Matricula matricula, Boolean isFinalizado);
}
