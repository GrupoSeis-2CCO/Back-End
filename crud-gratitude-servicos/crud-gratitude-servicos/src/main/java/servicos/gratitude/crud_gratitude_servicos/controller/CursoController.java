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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.CursoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;

import java.util.List;

@Tag(name = "Cursos", description = "Gerencia todas as operações relacionadas aos cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Cadastrar Curso", description = "Cadastra um novo curso no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class)))
    })
    public ResponseEntity<CursoResponseDto> cadastrarCurso(
            @Parameter(description = "Dados do curso", required = true)
            @Valid @RequestBody CursoRequestDto request
    ) {
        Curso curso = CursoMapper.toEntity(request);
        Curso cursoCadastrado = cursoService.cadastrarCurso(curso);
        CursoResponseDto response = CursoMapper.toEntity(cursoCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Listar Cursos", description = "Retorna a lista de todos os cursos disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum curso encontrado", content = @Content)
    })
    public ResponseEntity<List<CursoResponseDto>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();

        if (cursos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CursoMapper.toEntity(cursos));
    }

    @PutMapping("/atualizar/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Atualizar Curso", description = "Atualiza os dados de um curso existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<CursoResponseDto> atualizarCurso(
            @Parameter(description = "Novos dados do curso", required = true)
            @Valid @RequestBody CursoRequestDto request,
            @Parameter(description = "ID do curso a ser atualizado", required = true)
            @PathVariable Integer id
    ) {
        if (!cursoService.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Curso curso = CursoMapper.toEntity(request);
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        CursoResponseDto response = CursoMapper.toEntity(cursoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CursoResponseDto> atualizarOculto(
            @PathVariable Integer id
    ){
        if (!cursoService.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        Boolean cursoIsOculto = cursoService.cursoIsOculto(id);
        Curso ocultoAtualizado = cursoService.atualizarOculto(id, !cursoIsOculto);
        CursoResponseDto response = CursoMapper.toEntity(ocultoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Deletar Curso", description = "Remove um curso do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletarCurso(
            @Parameter(description = "ID do curso a ser deletado", required = true)
            @PathVariable Integer id
    ) {
        if (!cursoService.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        cursoService.deletarCurso(id);
        return ResponseEntity.status(200).build();
    }
}