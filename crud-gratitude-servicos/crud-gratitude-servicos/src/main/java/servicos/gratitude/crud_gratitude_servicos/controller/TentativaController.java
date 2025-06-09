package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.TentativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa.TentativaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.tentativa.TentativaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.MatriculaMapper;
import servicos.gratitude.crud_gratitude_servicos.mapper.TentativaMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.MatriculaService;
import servicos.gratitude.crud_gratitude_servicos.service.TentativaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tentativas")
public class TentativaController {
    private final TentativaService tentativaService;
    private final MatriculaService matriculaService;
    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<TentativaResponseDto> cadastrarTentativa(
            @Valid @RequestBody TentativaRequestDto request
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(request.getFkUsuario(), request.getFkCurso());
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Optional<Avaliacao> avaliacao = avaliacaoService.findById(request.getFkAvaliacao());

        if (avaliacao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Tentativa> tentativas = tentativaService.listarTentativasPorMatricula(matricula.get());
        Integer maiorId = 0;
        for (Tentativa tentativaDaVez : tentativas) {
            if (tentativaDaVez.getIdTentativaComposto().getIdTentativa() > maiorId){
                maiorId = tentativaDaVez.getIdTentativaComposto().getIdTentativa();
            }
        }
        Integer idTentativa = maiorId + 1;

        TentativaCompoundKey idTentativaComposto = TentativaMapper.toEntity(idTentativa, idMatriculaComposto);

        if (tentativaService.existsById(idTentativaComposto)){
            return ResponseEntity.status(409).build();
        }

        Tentativa tentativa = TentativaMapper.toEntity(request, idTentativaComposto, matricula.get(), avaliacao.get());
        Tentativa tentativaCadastrada = tentativaService.cadastrarTentativa(tentativa);
        TentativaResponseDto response = TentativaMapper.toEntity(tentativaCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("listar-por-usuario/{fkUsuario}/{fkCurso}")
    public ResponseEntity<List<TentativaResponseDto>> listarTentativasPorUsuario(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Tentativa> tentativas = tentativaService.listarTentativasPorMatricula(matricula.get());

        if (tentativas.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Tentativa> tentativasFiltradas = new ArrayList<>();

        for (Tentativa tentativaDaVez : tentativas) {
            if (tentativaDaVez.getIdTentativaComposto().getIdMatriculaComposto().getFkUsuario().equals(fkUsuario)){
                tentativasFiltradas.add(tentativaDaVez);
            }
        }

        List<TentativaResponseDto> responses = TentativaMapper.toEntity(tentativasFiltradas);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("listar-por-curso/{fkUsuario}/{fkCurso}")
    public ResponseEntity<List<TentativaResponseDto>> listarTentativasPorCurso(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Tentativa> tentativas = tentativaService.listarTentativasPorMatricula(matricula.get());

        if (tentativas.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Tentativa> tentativasFiltradas = new ArrayList<>();

        for (Tentativa tentativaDaVez : tentativas) {
            if (tentativaDaVez.getIdTentativaComposto().getIdMatriculaComposto().getFkUsuario().equals(fkCurso)){
                tentativasFiltradas.add(tentativaDaVez);
            }
        }

        List<TentativaResponseDto> responses = TentativaMapper.toEntity(tentativasFiltradas);
        return ResponseEntity.status(200).build();
    }
}
