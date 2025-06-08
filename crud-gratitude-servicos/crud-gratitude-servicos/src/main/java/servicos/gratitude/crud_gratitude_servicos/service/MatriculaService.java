package servicos.gratitude.crud_gratitude_servicos.service;

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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public Matricula cadastrarMatricula(Matricula matricula){
        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarMatriculas(){
        return matriculaRepository.findAll();
    }

    public List<Matricula> listarMatriculasPorUsuario(Usuario usuario){
        return matriculaRepository.findAllByUsuario(usuario);
    }

    public List<Matricula> listarMatriculasPorCurso(Curso curso){
        return matriculaRepository.findAllByCurso(curso);
    }

    public List<Matricula> listarMatriculasPorCompletude(Boolean isCompleta){
        return matriculaRepository.findAllByIsCompleto(isCompleta);
    }

    public Optional<Matricula> findById(MatriculaCompoundKey idMatricula){
        return matriculaRepository.findById(idMatricula);
    }

    public Matricula atualizarUltimoAcesso (Matricula matricula){
        matricula.setUltimoAcesso(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return matriculaRepository.save(matricula);
    }

    public Matricula completarMatricula (Matricula matricula){
        matricula.setIsCompleto(true);
        matricula.setDataFinalizacao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return matriculaRepository.save(matricula);
    }

    public void deletarMatricula (MatriculaCompoundKey idMatricula){
        matriculaRepository.deleteById(idMatricula);
    }
}
