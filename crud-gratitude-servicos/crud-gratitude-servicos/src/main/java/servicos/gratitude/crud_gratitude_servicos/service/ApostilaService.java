package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.repository.ApostilaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApostilaService {

    final private ApostilaRepository apostilaRepository;

    public Apostila cadastrarApostila(Apostila apostila){
        return apostilaRepository.save(apostila);
    }

    public List<Apostila> listarApostila(){
        return apostilaRepository.findAll();
    }

    public List<Apostila> listarApostilaPorCurso(Curso fkCurso){
        return apostilaRepository.findAllByFkCurso(fkCurso);
    }

    public Optional<Apostila> findById(Integer id){
        return apostilaRepository.findById(id);
    }

    public Apostila atualizarapostila(Apostila apostila, Integer id){
        apostila.setIdApostila(id);
        return apostilaRepository.save(apostila);
    }

    public Boolean existsById(Integer id){
        return apostilaRepository.existsById(id);
    }

    public void deletarApostila(Integer id){
        apostilaRepository.deleteById(id);
    }

    public Boolean isOculto(Integer id){
        return apostilaRepository.findById(id).get().getIsApostilaOculto();
    }

    public Apostila atualizarOculto(Integer id, Boolean novoOculto){
        apostilaRepository.findById(id).get().setIsApostilaOculto(novoOculto);
        return apostilaRepository.findById(id).get();
    }
}
