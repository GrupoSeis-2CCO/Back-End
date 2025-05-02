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
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoAcertosDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.AvaliacaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;

import java.util.Optional;
import java.util.Map;

@Tag(name = "Avaliação", description = "Gerencia todas as operações relacionadas às avaliações")
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;
    private final CursoService cursoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService, CursoService cursoService) {
        this.avaliacaoService = avaliacaoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Avaliação", description = "Método responsável por cadastrar novas avaliações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvaliacaoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<?> cadastrarAvaliacao(
            @Parameter(description = "Dados da avaliação que será cadastrada", required = true)
            @Valid @RequestBody AvaliacaoRequestDto request
    ) {
        Optional<Curso> curso = cursoService.findById(request.getIdCurso());

        if (curso.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "404",
                    "mensagem", "Curso não encontrado. Verifique o ID informado."
            ));
        }

        Avaliacao avaliacao = AvaliacaoMapper.toEntity(curso.get());
        Avaliacao avaliacaoCadastrada = avaliacaoService.cadastrarAvaliacao(avaliacao);
        AvaliacaoResponseDto response = AvaliacaoMapper.toEntity(avaliacaoCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/atualizar-acertos/{id}")
    @Operation(summary = "Atualizar Acertos Mínimos", description = "Atualiza o número mínimo de acertos para uma avaliação específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acertos mínimos atualizados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvaliacaoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada", content = @Content)
    })
    public ResponseEntity<?> atualizarAcertosMinimos(
            @Parameter(description = "Novo número mínimo de acertos", required = true)
            @Valid @RequestBody AvaliacaoAcertosDto novoMinimo,
            @Parameter(description = "ID da avaliação a ser atualizada", required = true)
            @PathVariable Integer id
    ) {
        Optional<Avaliacao> avaliacao = avaliacaoService.findById(id);

        if (avaliacao.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "404",
                    "mensagem", "Avaliação não encontrada. Verifique o ID informado."
            ));
        }

        Avaliacao avaliacaoParaAtualizar = AvaliacaoMapper.toEntity(avaliacao.get(), novoMinimo);
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizarAcertosMinimos(avaliacaoParaAtualizar, id);
        AvaliacaoResponseDto response = AvaliacaoMapper.toEntity(avaliacaoAtualizada);

        return ResponseEntity.status(200).body(response);
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