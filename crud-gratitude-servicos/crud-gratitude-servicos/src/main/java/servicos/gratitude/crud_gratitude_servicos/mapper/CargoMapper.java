package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CargoMapper {

    static public Cargo toEntity(CargoUpdateDto update){
        Cargo cargo = new Cargo();

        cargo.setNomeCargo(update.getNomeCargo());

        return cargo;
    }

    static public CargoResponseDto toEntity(Cargo cargo){
        CargoResponseDto response = new CargoResponseDto();

        response.setNomeCargo(cargo.getNomeCargo());

        return response;
    }

    static public List<CargoResponseDto> toEntity (List<Cargo> cargos){
        List<CargoResponseDto> responses = new ArrayList<>();

        for (Cargo cargoDaVez : cargos) {
            CargoResponseDto response = new CargoResponseDto();

            response.setNomeCargo(cargoDaVez.getNomeCargo());

            responses.add(response);
        }

        return responses;
    }
}
