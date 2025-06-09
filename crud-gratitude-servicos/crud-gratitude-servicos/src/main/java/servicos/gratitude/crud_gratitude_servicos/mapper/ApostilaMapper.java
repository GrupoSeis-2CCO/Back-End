package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaUpdateDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ApostilaMapper {

    static public Apostila toEntity(ApostilaRequestDto request, Integer ordem, Curso curso){
        Apostila apostila = new Apostila();
        LocalDateTime dataCriacao = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        apostila.setNomeApostilaOriginal(request.getNomeApostila());
        apostila.setNomeApostilaArmazenamento(
                ("%s-gratitudeCapacita").formatted(String.valueOf(dataCriacao))
        );
        apostila.setDescricaoApostila(request.getDescricaoApostila());
        apostila.setTamanhoBytes(request.getTamanhoBytes());
        apostila.setDataPostadoApostila(dataCriacao);
        apostila.setOrdemApostila(ordem);
        apostila.setIsApostilaOculto(true);
        apostila.setFkCurso(curso);

        return apostila;
    }

    static public ApostilaResponseDto toEntity(Apostila apostila, String nomeCurso){
        ApostilaResponseDto response = new ApostilaResponseDto();

        response.setCurso(nomeCurso);
        response.setNome(apostila.getNomeApostilaOriginal());
        response.setNomeEmArmazenamento(apostila.getNomeApostilaArmazenamento());
        response.setDescricao(apostila.getDescricaoApostila());
        response.setTamanhoBytes(apostila.getTamanhoBytes());
        response.setDataPostagem(apostila.getDataPostadoApostila());
        response.setIsOculto(apostila.getIsApostilaOculto());
        response.setOrdem(apostila.getOrdemApostila());

        return response;
    }

    static public List<ApostilaResponseDto> toEntity(List<Apostila> materiais, String nomeCurso){
        List<ApostilaResponseDto> responses = new ArrayList<>();

        for (Apostila apostilaDaVez : materiais) {
            ApostilaResponseDto response = new ApostilaResponseDto();

            response.setCurso(nomeCurso);
            response.setNome(apostilaDaVez.getNomeApostilaOriginal());
            response.setNomeEmArmazenamento(apostilaDaVez.getNomeApostilaArmazenamento());
            response.setDescricao(apostilaDaVez.getDescricaoApostila());
            response.setTamanhoBytes(apostilaDaVez.getTamanhoBytes());
            response.setDataPostagem(apostilaDaVez.getDataPostadoApostila());
            response.setIsOculto(apostilaDaVez.getIsApostilaOculto());
            response.setOrdem(apostilaDaVez.getOrdemApostila());

            responses.add(response);
        }

        return responses;
    }

    static public Apostila toEntity(ApostilaUpdateDto update, Apostila apostila){
        Apostila apostilaAtualizado = new Apostila();

        apostilaAtualizado.setNomeApostilaOriginal(update.getNomeApostilaOriginal());
        apostilaAtualizado.setDescricaoApostila(update.getDescricaoApostila());
        apostilaAtualizado.setOrdemApostila(update.getOrdemApostila());

        apostilaAtualizado.setIdApostila(apostila.getIdApostila());
        apostilaAtualizado.setNomeApostilaArmazenamento(apostila.getNomeApostilaArmazenamento());
        apostilaAtualizado.setTamanhoBytes(apostila.getTamanhoBytes());
        apostilaAtualizado.setDataPostadoApostila(apostila.getDataPostadoApostila());
        apostilaAtualizado.setDataAtualizacaoApostila(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        apostilaAtualizado.setIsApostilaOculto(apostila.getIsApostilaOculto());
        apostilaAtualizado.setFkCurso(apostilaAtualizado.getFkCurso());

        return apostilaAtualizado;
    }
}
