package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Video;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.video.VideoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.VideoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.VideoService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;
    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cadastrar Video", description = "Cadastra um novo video para um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Video cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<VideoResponseDto> cadastrarVideo(
            @Parameter(description = "Dados do video", required = true)
            @Valid @RequestBody VideoRequestDto request
    ) {
        Optional<Curso> curso = cursoService.findById(request.getFkCurso());

        if (curso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        List<Video> videos = videoService.listarVideoPorCurso(curso.get());
        Integer maiorOrdem = 0;
        for (Video videoDaVez : videos) {
            if (videoDaVez.getOrdemVideo() > maiorOrdem){
                maiorOrdem = videoDaVez.getOrdemVideo();
            }
        }
        Integer ordem = maiorOrdem + 1;

        Video video = VideoMapper.toEntity(request, ordem, curso.get());
        Video videoCadastrado = videoService.cadastrarVideo(video);
        VideoResponseDto response = VideoMapper.toEntity(videoCadastrado, curso.get().getTituloCurso());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{fkCurso}")
    @Operation(summary = "Listar Videos por Curso", description = "Retorna a lista de todos os videos de um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de videos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VideoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum video encontrado", content = @Content)
    })
    public ResponseEntity<List<VideoResponseDto>> listarVideosPorCurso(
            @PathVariable Integer fkCurso
    ) {
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if (curso.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<Video> videos = videoService.listarVideoPorCurso(curso.get());

        if (videos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(VideoMapper.toEntity(videos, curso.get().getTituloCurso()));
    }

    @PutMapping("/atualizar-dados/{id}")
    public ResponseEntity<VideoResponseDto> atualizarDadosVideo(
            @Valid @RequestBody VideoUpdateDto update,
            @PathVariable Integer idVideo
    ){
        Optional<Video> video = videoService.findById(idVideo);

        if (video.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Video videoParaAtualizar = VideoMapper.toEntity(update, video.get());
        Video videoAtualizado = videoService.atualizarvideo(videoParaAtualizar, idVideo);
        VideoResponseDto response = VideoMapper.toEntity(videoAtualizado, video.get().getCurso().getTituloCurso());

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/atualizar-oculto/{id}")
    public ResponseEntity<VideoResponseDto> atualizarOcultoVideo(
            @PathVariable Integer idVideo
    ){
        if (!videoService.existsById(idVideo)){
            return ResponseEntity.status(404).build();
        }

        Boolean videoIsOculto = videoService.isOculto(idVideo);
        Video ocultoAtualizado = videoService.atualizarOculto(idVideo, !videoIsOculto);
        VideoResponseDto response = VideoMapper.toEntity(ocultoAtualizado, ocultoAtualizado.getCurso().getTituloCurso());

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarVideo(
            @PathVariable Integer idVideo
    ){
        if (!videoService.existsById(idVideo)){
            return ResponseEntity.status(404).build();
        }

        videoService.deletarVideo(idVideo);

        return ResponseEntity.status(200).build();
    }
}
