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
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.apostila.ApostilaUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.ApostilaMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.ApostilaService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apostilas")
public class ApostilaController {

    private final ApostilaService apostilaService;
    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cadastrar Apostila", description = "Cadastra uma nova apostila para um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Apostila cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApostilaResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content)
    })
    public ResponseEntity<ApostilaResponseDto> cadastrarApostila(
            @Parameter(description = "Dados da apostila", required = true)
            @Valid @RequestBody ApostilaRequestDto request
    ) {
        Optional<Curso> curso = cursoService.findById(request.getFkCurso());

        if (curso.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        List<Apostila> apostilas = apostilaService.listarApostilaPorCurso(curso.get());
        Integer maiorOrdem = 0;
        for (Apostila apostilaDaVez : apostilas) {
            if (apostilaDaVez.getOrdemApostila() > maiorOrdem){
                maiorOrdem = apostilaDaVez.getOrdemApostila();
            }
        }
        Integer ordem = maiorOrdem + 1;

        Apostila apostila = ApostilaMapper.toEntity(request, ordem,  curso.get());
        Apostila apostilaCadastrado = apostilaService.cadastrarApostila(apostila);
        ApostilaResponseDto response = ApostilaMapper.toEntity(apostilaCadastrado, curso.get().getTituloCurso());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{fkCurso}")
    @Operation(summary = "Listar Apostilas por Curso", description = "Retorna a lista de todas as apostilas de um curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de apostilas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApostilaResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum apostila encontrada", content = @Content)
    })
    public ResponseEntity<List<ApostilaResponseDto>> listarApostilasPorCurso(
            @PathVariable Integer fkCurso
    ) {
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if (curso.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<Apostila> apostilas = apostilaService.listarApostilaPorCurso(curso.get());

        if (apostilas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ApostilaMapper.toEntity(apostilas, curso.get().getTituloCurso()));
    }

    @PutMapping("/atualizar-dados/{id}")
    public ResponseEntity<ApostilaResponseDto> atualizarDadosApostila(
            @Valid @RequestBody ApostilaUpdateDto update,
            @PathVariable Integer idApostila
    ){
        Optional<Apostila> apostila = apostilaService.findById(idApostila);

        if (apostila.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Apostila apostilaParaAtualizar = ApostilaMapper.toEntity(update, apostila.get());
        Apostila apostilaAtualizado = apostilaService.atualizarapostila(apostilaParaAtualizar, idApostila);
        ApostilaResponseDto response = ApostilaMapper.toEntity(apostilaAtualizado, apostila.get().getFkCurso().getTituloCurso());

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/atualizar-oculto/{id}")
    public ResponseEntity<ApostilaResponseDto> atualizarOcultoApostila(
            @PathVariable Integer idApostila
    ){
        if (!apostilaService.existsById(idApostila)){
            return ResponseEntity.status(404).build();
        }

        Boolean apostilaIsOculto = apostilaService.isOculto(idApostila);
        Apostila ocultoAtualizado = apostilaService.atualizarOculto(idApostila, !apostilaIsOculto);
        ApostilaResponseDto response = ApostilaMapper.toEntity(ocultoAtualizado, ocultoAtualizado.getFkCurso().getTituloCurso());

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarApostila(
            @PathVariable Integer idApostila
    ){
        if (!apostilaService.existsById(idApostila)){
            return ResponseEntity.status(404).build();
        }

        apostilaService.deletarApostila(idApostila);

        return ResponseEntity.status(200).build();
    }
}
