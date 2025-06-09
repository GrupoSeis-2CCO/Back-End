package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.MaterialAluno;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MaterialAlunoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.MaterialAlunoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MaterialAlunoService {
    private final MaterialAlunoRepository materialAlunoRepository;

    public MaterialAluno cadastrarMaterialAluno(MaterialAluno materialAluno){
        return materialAlunoRepository.save(materialAluno);
    }

    public List<MaterialAluno> listarMaterialAlunoPorMatricula(Matricula matricula){
        return materialAlunoRepository.findAllByMatricula(matricula);
    }

    public List<MaterialAluno> listarMaterialAlunoPorMatriculaEFinalizacao(Matricula matricula, Boolean isFinalizado){
        return materialAlunoRepository.findAllByMatriculaAndIsFinalizado(matricula, isFinalizado);
    }

    public MaterialAluno atualizarIsFinalizado(MaterialAluno materialAluno){
        materialAluno.setIsFinalizado(!materialAluno.getIsFinalizado());
        return materialAlunoRepository.save(materialAluno);
    }

    public MaterialAluno atualizarUltimoAcesso(MaterialAluno materialAluno, LocalDateTime ultimoAcesso){
        materialAluno.setUltimoAcesso(ultimoAcesso);
        return materialAlunoRepository.save(materialAluno);
    }

    public void deletarMaterialAluno(MaterialAlunoCompoundKey idMaterialAlunoComposto){
        materialAlunoRepository.deleteById(idMaterialAlunoComposto);
    }

    public Optional<MaterialAluno> findById(MaterialAlunoCompoundKey idMaterialAlunoComposto){
        return materialAlunoRepository.findById(idMaterialAlunoComposto);
    }

    public Boolean existsById(MaterialAlunoCompoundKey idMaterialAlunoComposto){
        return materialAlunoRepository.existsById(idMaterialAlunoComposto);
    }
}
