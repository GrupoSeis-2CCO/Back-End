package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.cargo.CargoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.CargoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CargoService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Tag(name = "Cargos", description = "Gerencia todas as operações relacionadas aos cargos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cargos")
public class CargoController {
    private final CargoService cargoService;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Listar Cargos", description = "Retorna a lista de todos os cargos disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cargos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CargoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum cargo encontrado", content = @Content)
    })
    public ResponseEntity<?> listarCargos() {
        List<Cargo> cargos = cargoService.listarCargos();

        if (cargos.isEmpty()) {
            return ResponseEntity.status(204).body(Map.of("status", "204", "message", "Nenhum cargo encontrado."));
        }

        List<CargoResponseDto> responses = CargoMapper.toEntity(cargos);
        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Atualizar Nome do Cargo", description = "Atualiza o nome de um cargo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nome do cargo atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CargoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado", content = @Content)
    })
    public ResponseEntity<?> atualizarNomeCargo(
            @Parameter(description = "Novo nome do cargo", required = true)
            @Valid @RequestBody CargoUpdateDto novoNome,
            @Parameter(description = "ID do cargo a ser atualizado", required = true)
            @PathVariable Integer id
    ) {
        if (!cargoService.cargoExistById(id)) {
            return ResponseEntity.status(404).body(Map.of("status", "404", "message", "Cargo não encontrado. Verifique o ID informado."));
        }

        Cargo cargo = CargoMapper.toEntity(novoNome);
        Cargo nomeAtualizado = cargoService.atualizarNomeCargo(id, cargo);
        CargoResponseDto response = CargoMapper.toEntity(nomeAtualizado);

        return ResponseEntity.status(200).body(response);
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> ErroValidacao(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", "400",
                "mensagem", "Dados inválidos. Verifique os campos obrigatórios."
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> ErroBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", "400",
                "mensagem", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> ErroInterno(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "500",
                "mensagem", "Erro interno no servidor. Contate o suporte."
        ));
    }
}