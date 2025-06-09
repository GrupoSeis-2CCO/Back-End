package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Video;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoUpdateDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class VideoMapper {

    static public Video toEntity(VideoRequestDto request, Integer ordem, Curso curso){
        Video video = new Video();

        video.setNomeVideo(request.getNomeVideo());
        video.setDescricaoVideo(request.getDescricaoVideo());
        video.setUrlVideo(request.getUrlVideo());
        video.setDataPostadoVideo(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        video.setOrdemVideo(ordem);
        video.setIsVideoOculto(true);
        video.setFkCurso(curso);

        return video;
    }

    static public VideoResponseDto toEntity(Video video, String nomeCurso){
        VideoResponseDto response = new VideoResponseDto();

        response.setCurso(nomeCurso);
        response.setNome(video.getNomeVideo());
        response.setDescricao(video.getDescricaoVideo());
        response.setUrl(video.getUrlVideo());
        response.setDataPostagem(video.getDataPostadoVideo());
        response.setIsOculto(video.getIsVideoOculto());
        response.setOrdem(video.getOrdemVideo());

        return response;
    }

    static public List<VideoResponseDto> toEntity(List<Video> materiais, String nomeCurso){
        List<VideoResponseDto> responses = new ArrayList<>();

        for (Video videoDaVez : materiais) {
            VideoResponseDto response = new VideoResponseDto();

            response.setCurso(nomeCurso);
            response.setNome(videoDaVez.getNomeVideo());
            response.setDescricao(videoDaVez.getDescricaoVideo());
            response.setUrl(videoDaVez.getUrlVideo());
            response.setDataPostagem(videoDaVez.getDataPostadoVideo());
            response.setIsOculto(videoDaVez.getIsVideoOculto());
            response.setOrdem(videoDaVez.getOrdemVideo());

            responses.add(response);
        }

        return responses;
    }

    static public Video toEntity(VideoUpdateDto update, Video video){
        Video videoAtualizado = new Video();

        videoAtualizado.setNomeVideo(update.getNomeVideo());
        videoAtualizado.setDescricaoVideo(update.getDescricaoVideo());
        videoAtualizado.setUrlVideo(update.getUrlVideo());
        videoAtualizado.setOrdemVideo(update.getOrdemVideo());

        videoAtualizado.setIdVideo(video.getIdVideo());
        videoAtualizado.setDataPostadoVideo(video.getDataPostadoVideo());
        videoAtualizado.setDataAtualizacaoVideo(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        videoAtualizado.setIsVideoOculto(video.getIsVideoOculto());
        videoAtualizado.setFkCurso(videoAtualizado.getFkCurso());

        return videoAtualizado;
    }
}
