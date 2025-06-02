package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.repository.CargoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;

    public List<Cargo> listarCargos(){
        return cargoRepository.findAll();
    }

    public Boolean cargoExistById(Integer id){
        return cargoRepository.existsById(id);
    }

    public Cargo atualizarNomeCargo(Integer id, Cargo cargo){
        cargo.setId_cargo(id);
        return cargoRepository.save(cargo);
    }

    public Optional<Cargo> findById(Integer id){
        return cargoRepository.findById(id);
    }
}
