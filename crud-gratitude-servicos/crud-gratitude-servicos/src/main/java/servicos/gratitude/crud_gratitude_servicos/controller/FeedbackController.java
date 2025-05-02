package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.FeedbackMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.FeedbackService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Feedbacks", description = "Gerencia todas as operações relacionadas aos feedbacks")
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final CursoService cursoService;

    public FeedbackController(FeedbackService feedbackService, CursoService cursoService) {
        this.feedbackService = feedbackService;
        this.cursoService = cursoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Feedback", description = "Cadastra um novo feedback para um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Feedback cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<FeedbackResponseDto> cadastrarFeedback(
            @Parameter(description = "Dados do feedback", required = true)
            @Valid @RequestBody FeedbackRequestDto request
    ) {
        Optional<Curso> cursoFeedback = cursoService.findById(request.getIdCurso());

        if (cursoFeedback.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Feedback feedback = FeedbackMapper.toEntity(request, cursoFeedback.get());
        Feedback feedbackCadastrado = feedbackService.cadastrarFeedback(feedback);
        FeedbackResponseDto response = FeedbackMapper.toEntity(feedbackCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar Feedbacks", description = "Retorna a lista de todos os feedbacks disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de feedbacks retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum feedback encontrado", content = @Content)
    })
    public ResponseEntity<List<FeedbackResponseDto>> listarFeedbacks() {
        List<Feedback> feedbacks = feedbackService.listarFeedbacks();

        if (feedbacks.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<FeedbackResponseDto> responses = FeedbackMapper.toEntity(feedbacks);

        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/{idCurso}")
    @Operation(summary = "Listar Feedbacks por Curso", description = "Retorna os feedbacks de um curso específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de feedbacks retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum feedback encontrado para o curso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<List<FeedbackResponseDto>> listarFeedbacksPorCurso(
            @Parameter(description = "ID do curso para filtrar feedbacks", required = true)
            @PathVariable Integer idCurso
    ) {
        Optional<Curso> curso = cursoService.findById(idCurso);

        if (curso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        List<Feedback> feedbacks = feedbackService.findByCurso(curso.get());

        if (feedbacks.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<FeedbackResponseDto> responses = FeedbackMapper.toEntity(feedbacks);

        return ResponseEntity.status(200).body(responses);
    }
}