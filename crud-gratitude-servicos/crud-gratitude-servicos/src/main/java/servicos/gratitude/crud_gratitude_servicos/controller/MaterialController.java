package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.MaterialMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.ExtensaoService;
import servicos.gratitude.crud_gratitude_servicos.service.MaterialService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Materiais", description = "Gerencia todas as operações relacionadas aos materiais")
@RequiredArgsConstructor
@RestController
@RequestMapping("/materiais")
public class MaterialController {
    private final ExtensaoService extensaoService;
    private final MaterialService materialService;
    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cadastrar Material", description = "Cadastra um novo material para um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MaterialResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso ou extensão não encontrados", content = @Content)
    })
    public ResponseEntity<MaterialResponseDto> cadastrarMaterial(
            @Parameter(description = "Dados do material", required = true)
            @Valid @RequestBody MaterialRequestDto request
    ) {
        Optional<Extensao> extensao = extensaoService.findById(request.getFk_extensao());
        Optional<Curso> curso = cursoService.findById(request.getFk_curso());

        if (extensao.isEmpty() || curso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Material material = MaterialMapper.toEntity(request, curso.get(), extensao.get());
        Material materialCadastrado = materialService.cadastrarMaterial(material);
        MaterialResponseDto response = MaterialMapper.toEntity(materialCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar Materiais", description = "Retorna a lista de todos os materiais disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de materiais retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MaterialResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum material encontrado", content = @Content)
    })
    public ResponseEntity<List<MaterialResponseDto>> listarMateriais() {
        List<Material> materiais = materialService.listarMaterial();

        if (materiais.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(MaterialMapper.toEntity(materiais));
    }

    @PutMapping("/atualizar-dados/{id}")
    public ResponseEntity<MaterialResponseDto> atualizarDadosMaterial(
            @Valid @RequestBody MaterialUpdateDto update,
            @PathVariable Integer idMaterial
    ){
        Optional<Material> material = materialService.findById(idMaterial);

        if (material.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Material materialParaAtualizar = MaterialMapper.toEntity(update, material.get());
        Material materialAtualizado = materialService.atualizarmaterial(materialParaAtualizar, idMaterial);
        MaterialResponseDto response = MaterialMapper.toEntity(materialAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/atualizar-oculto/{id}")
    public ResponseEntity<MaterialResponseDto> atualizarOcultoMaterial(
            @PathVariable Integer idMaterial
    ){
        if (!materialService.existsById(idMaterial)){
            return ResponseEntity.status(404).build();
        }

        Boolean materialIsOculto = materialService.isOculto(idMaterial);
        Material ocultoAtualizado = materialService.atualizarOculto(idMaterial, !materialIsOculto);
        MaterialResponseDto response = MaterialMapper.toEntity(ocultoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMaterial(
            @PathVariable Integer idMaterial
    ){
        if (!materialService.existsById(idMaterial)){
            return ResponseEntity.status(404).build();
        }

        materialService.deletarMaterial(idMaterial);

        return ResponseEntity.status(200).build();
    }
}