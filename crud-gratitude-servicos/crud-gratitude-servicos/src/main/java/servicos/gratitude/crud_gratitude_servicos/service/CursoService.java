package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public Optional<Curso> findByTitulo(String titulo){
        return cursoRepository.findByTituloCurso(titulo);
    }

    public Curso cadastrarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    public Boolean existsById(Integer id){
        return cursoRepository.existsById(id);
    }

    public Curso atualizarCurso(Integer id, Curso curso){
        curso.setIdCurso(id);
        return cursoRepository.save(curso);
    }

    public Boolean cursoIsOculto(Integer id){
        return cursoRepository.findById(id).get().getOcultado();
    }

    public Curso atualizarOculto(Integer id, Boolean ocultado){
        Curso curso = cursoRepository.findById(id).get();
        curso.setOcultado(ocultado);
        return cursoRepository.save(curso);
    }

    public void deletarCurso(Integer id){
        cursoRepository.deleteById(id);
    }

    public Optional<Curso> findById(Integer id){
        return cursoRepository.findById(id);
    }
}
