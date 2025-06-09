package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.feedback.FeedbackResponseDto;

import java.util.ArrayList;
import java.util.List;

public class FeedbackMapper {

    static public Feedback toEntity(FeedbackRequestDto request, Curso cursoFeedback, Usuario usuario){
        Feedback feedback = new Feedback();

        feedback.setFkCurso(cursoFeedback);
        feedback.setEstrelas(request.getEstrelas());
        feedback.setMotivo(request.getMotivo());
        feedback.setFkUsuario(usuario);

        return feedback;
    }

    static public FeedbackResponseDto toEntity(Feedback feedback){
        FeedbackResponseDto response = new FeedbackResponseDto();

        response.setNomeCurso(feedback.getFkCurso().getTituloCurso());
        response.setEstrelas(feedback.getEstrelas());
        response.setMotivo(feedback.getMotivo());

        if (feedback.getFkUsuario() != null){
            response.setUsuario(feedback.getFkUsuario().getNome());
        } else {
            response.setUsuario("Usuario Anônimo");
        }

        return response;
    }

    static public List<FeedbackResponseDto> toEntity(List<Feedback> feedbacks){
        List<FeedbackResponseDto> responses = new ArrayList<>();

        for (Feedback feedbackDaVez : feedbacks) {
            FeedbackResponseDto response = new FeedbackResponseDto();

            response.setNomeCurso(feedbackDaVez.getFkCurso().getTituloCurso());
            response.setEstrelas(feedbackDaVez.getEstrelas());
            response.setMotivo(feedbackDaVez.getMotivo());

            if (feedbackDaVez.getFkUsuario() != null){
            response.setUsuario(feedbackDaVez.getFkUsuario().getNome());
            } else {
                response.setUsuario("Usuario Anônimo");
            }

            responses.add(response);
        }

        return responses;
    }
}
