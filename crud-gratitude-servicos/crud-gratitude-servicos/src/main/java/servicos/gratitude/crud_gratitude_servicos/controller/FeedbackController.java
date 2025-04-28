package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.FeedbackMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.FeedbackService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<FeedbackResponseDto> cadastrarFeedback(
            @Valid @RequestBody FeedbackRequestDto request
    ){
        Optional<Curso> cursoFeedback = cursoService.findById(request.getIdCurso());

        if (cursoFeedback.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Feedback feedback = FeedbackMapper.toEntity(request, cursoFeedback.get());
        Feedback feedbackCadastrado = feedbackService.cadastrarFeedback(feedback);
        FeedbackResponseDto response = FeedbackMapper.toEntity(feedbackCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDto>> listarFeedbacks(){
        List<Feedback> feedbacks = feedbackService.listarFeedbacks();

        if (feedbacks.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<FeedbackResponseDto> responses = FeedbackMapper.toEntity(feedbacks);

        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FeedbackResponseDto>> listarFeedbacksPorCurso(
            @PathVariable Integer idCurso
    ){
        Optional<Curso> curso = cursoService.findById(idCurso);

        if (curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Feedback> feedbacks = feedbackService.findByCurso(curso.get());

        if (feedbacks.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<FeedbackResponseDto> responses = FeedbackMapper.toEntity(feedbacks);

        return ResponseEntity.status(200).body(responses);
    }
}
