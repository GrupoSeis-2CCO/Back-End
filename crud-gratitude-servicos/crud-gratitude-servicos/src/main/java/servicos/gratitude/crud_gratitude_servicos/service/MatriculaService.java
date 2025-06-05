package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.MatriculaRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public Matricula cadastrarMatricula(Matricula matricula){
        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarMatriculaPorUsuario(Usuario usuario){
        return matriculaRepository.findMatriculaByUsuario(usuario);
    }

    public List<Matricula> listarMatriculaPorCurso(Curso curso){
        return matriculaRepository.findMatriculaByCurso(curso);
    }

    public Matricula AtualizarUltimoAcesso (Matricula matricula){
        matricula.setUltimoAcesso(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return matriculaRepository.save(matricula);
    }

    public Matricula finalizarMatricula (Matricula matricula){
        matricula.setIsCompleto(true);
        matricula.setDataFinalizacao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return matriculaRepository.save(matricula);
    }

    public void deletarMatricula (MatriculaCompoundKey idMatricula){
        matriculaRepository.deleteById(idMatricula);
    }
}
