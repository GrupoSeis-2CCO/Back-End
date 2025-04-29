package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
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
        curso.setId_curso(id);
        return cursoRepository.save(curso);
    }

    public Boolean cursoIsOculto(Integer id){
        return cursoRepository.findById(id).get().getOcultado();
    }

    public Curso atualizarOculto(Integer id, Boolean ocultado){
        cursoRepository.findById(id).get().setOcultado(ocultado);
        return cursoRepository.findById(id).get();
    }

    public void deletarCurso(Integer id){
        cursoRepository.deleteById(id);
    }

    public Optional<Curso> findById(Integer id){
        return cursoRepository.findById(id);
    }
}
