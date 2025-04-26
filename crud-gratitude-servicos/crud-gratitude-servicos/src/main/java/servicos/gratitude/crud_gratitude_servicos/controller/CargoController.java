package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.CargoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CargoService;

import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<List<CargoResponseDto>> listarCargos(){
        List<Cargo> cargos = cargoService.listarCargos();

        if (cargos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<CargoResponseDto> responses = CargoMapper.toEntity(cargos);

        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDto> atualizarNomeCargo(
            @Valid @RequestBody CargoUpdateDto novoNome,
            @PathVariable Integer id
    ){
        if (!cargoService.cargoExistById(id)){
            return ResponseEntity.status(404).build();
        }

        Cargo cargo = CargoMapper.toEntity(novoNome);

        Cargo nomeAtualizado = cargoService.atualizarNomeCargo(id, cargo);

        CargoResponseDto response = CargoMapper.toEntity(nomeAtualizado);

        return ResponseEntity.status(200).body(response);
    }
}
