package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.CursoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;

import java.util.List;
import java.util.Map;

@Tag(name = "Cursos", description = "Gerencia todas as operações relacionadas aos cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Curso", description = "Cadastra um novo curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar curso", content = @Content)
    })
    public ResponseEntity<Map<String, String>> cadastrarCurso(@Valid @RequestBody CursoRequestDto request) {
        try {
            Curso curso = CursoMapper.toEntity(request);
            cursoService.cadastrarCurso(curso);
            return ResponseEntity.status(201).body(Map.of(
                    "status", "201",
                    "mensagem", "Curso cadastrado com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "500",
                    "mensagem", "Erro interno ao cadastrar curso. Contate o suporte."
            ));
        }
    }

    @GetMapping
    @Operation(summary = "Listar Cursos", description = "Retorna a lista de todos os cursos disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CursoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum curso encontrado", content = @Content)
    })
    public ResponseEntity<Map<String, Object>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();

        if (cursos.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "404",
                    "mensagem", "Nenhum curso encontrado."
            ));
        }

        return ResponseEntity.status(200).body(Map.of(
                "status", "200",
                "mensagem", "Lista de cursos retornada com sucesso.",
                "dados", CursoMapper.toEntity(cursos)
        ));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar Curso", description = "Atualiza as informações de um curso existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar curso", content = @Content)
    })
    public ResponseEntity<Map<String, String>> atualizarCurso(@Valid @RequestBody CursoRequestDto request, @PathVariable Integer id) {
        if (!cursoService.existsById(id)) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "404",
                    "mensagem", "Curso não encontrado."
            ));
        }

        try {
            cursoService.atualizarCurso(id, CursoMapper.toEntity(request));
            return ResponseEntity.status(200).body(Map.of(
                    "status", "200",
                    "mensagem", "Curso atualizado com sucesso!",
                    "idCurso", id.toString()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "500",
                    "mensagem", "Erro interno ao atualizar curso."
            ));
        }
    }
}