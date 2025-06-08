package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Video;
import servicos.gratitude.crud_gratitude_servicos.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    final private VideoRepository videoRepository;

    public Video cadastrarVideo(Video video){
        return videoRepository.save(video);
    }

    public List<Video> listarVideo(){
        return videoRepository.findAll();
    }

    public List<Video> listarVideoPorCurso(Curso fkCurso){
        return videoRepository.findAllByFkCurso(fkCurso);
    }

    public Optional<Video> findById(Integer id){
        return videoRepository.findById(id);
    }

    public Video atualizarvideo(Video video, Integer id){
        video.setIdVideo(id);
        return videoRepository.save(video);
    }

    public Boolean existsById(Integer id){
        return videoRepository.existsById(id);
    }

    public void deletarVideo(Integer id){
        videoRepository.deleteById(id);
    }

    public Boolean isOculto(Integer id){
        return videoRepository.findById(id).get().getIsVideoOculto();
    }

    public Video atualizarOculto(Integer id, Boolean novoOculto){
        videoRepository.findById(id).get().setIsVideoOculto(novoOculto);
        return videoRepository.findById(id).get();
    }
}
