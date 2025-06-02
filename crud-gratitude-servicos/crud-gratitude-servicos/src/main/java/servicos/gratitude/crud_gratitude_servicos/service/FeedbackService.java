package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.repository.FeedbackRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Feedback cadastrarFeedback(Feedback feedback){
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> listarFeedbacks(){
        return feedbackRepository.findAll();
    }

    public List<Feedback> findByCurso(Curso fkCurso){
        return feedbackRepository.findByFkCurso(fkCurso);
    }
}