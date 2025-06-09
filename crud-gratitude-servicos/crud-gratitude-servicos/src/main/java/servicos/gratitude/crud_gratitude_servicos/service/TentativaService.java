package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.TentativaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TentativaService {
    private final TentativaRepository tentativaRepository;

    public Tentativa cadastrarTentativa(Tentativa tentativa){
        return tentativaRepository.save(tentativa);
    }

    public List<Tentativa> listarTentativasPorMatricula(Matricula matricula){
        return tentativaRepository.findAllByMatricula(matricula);
    }

    public Optional<Tentativa> findById(TentativaCompoundKey idTentativaComposto){
        return tentativaRepository.findById(idTentativaComposto);
    }

    public Boolean existsById(TentativaCompoundKey idTentativaComposto){
        return tentativaRepository.existsById(idTentativaComposto);
    }
}
