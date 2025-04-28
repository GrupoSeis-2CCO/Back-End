package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.avaliacao.AvaliacaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.AvaliacaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvalicaoController {

    private final AvaliacaoService avaliacaoService;
    private final CursoService cursoService;

    public AvalicaoController(AvaliacaoService avaliacaoService, CursoService cursoService) {
        this.avaliacaoService = avaliacaoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDto> cadastrarAvaliacao(
            @Valid @RequestBody AvaliacaoRequestDto request
    ){
        Optional<Curso> curso = cursoService.findById(request.getIdCurso());

        if (curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Avaliacao avaliacao = AvaliacaoMapper.toEntity(curso.get());
        Avaliacao avaliacaoCadastrada = avaliacaoService.cadastrarAvaliacao(avaliacao);
        AvaliacaoResponseDto response = AvaliacaoMapper.toEntity(avaliacaoCadastrada);

        return ResponseEntity.status(201).body(response);
    }
}
