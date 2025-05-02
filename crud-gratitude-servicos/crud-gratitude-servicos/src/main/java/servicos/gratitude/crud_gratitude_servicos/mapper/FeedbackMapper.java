package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackResponseDto;

import java.util.ArrayList;
import java.util.List;

public class FeedbackMapper {

    static public Feedback toEntity(FeedbackRequestDto request, Curso cursoFeedback){
        Feedback feedback = new Feedback();

        feedback.setFkCurso(cursoFeedback);
        feedback.setEstrelas(request.getEstrelas());
        feedback.setMotivo(request.getMotivo());

        return feedback;
    }

    static public FeedbackResponseDto toEntity(Feedback feedback){
        FeedbackResponseDto response = new FeedbackResponseDto();

        response.setIdCurso(feedback.getFkCurso().getId_curso());
        response.setNomeCurso(feedback.getFkCurso().getTitulo_curso());
        response.setEstrelas(feedback.getEstrelas());
        response.setMotivo(feedback.getMotivo());

        return response;
    }

    static public List<FeedbackResponseDto> toEntity(List<Feedback> feedbacks){
        List<FeedbackResponseDto> responses = new ArrayList<>();

        for (Feedback feedbackDaVez : feedbacks) {
            FeedbackResponseDto response = new FeedbackResponseDto();

            response.setIdCurso(feedbackDaVez.getFkCurso().getId_curso());
            response.setNomeCurso(feedbackDaVez.getFkCurso().getTitulo_curso());
            response.setEstrelas(feedbackDaVez.getEstrelas());
            response.setMotivo(feedbackDaVez.getMotivo());

            responses.add(response);
        }

        return responses;
    }
}
