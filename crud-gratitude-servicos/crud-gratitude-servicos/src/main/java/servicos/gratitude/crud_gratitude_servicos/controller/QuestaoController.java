package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoAtualizacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.QuestaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.QuestaoService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    private final QuestaoService questaoService;
    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<QuestaoResponseDto> cadastrarQuestao(
            @Valid @RequestBody QuestaoRequestDto request
    ){
        Optional<Avaliacao> avaliacao = avaliacaoService.findById(request.getFkAvaliacao());

        if (avaliacao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(request.getIdQuestao(), request.getFkAvaliacao());

        if (questaoService.existsById(idComposto)){
            return ResponseEntity.status(409).build();
        }

        Questao questao = QuestaoMapper.toEntity(idComposto, request, avaliacao.get());
        Questao questaoCadastrada = questaoService.cadastrarQuestao(questao);
        QuestaoResponseDto response = QuestaoMapper.toEntity(questaoCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/{idAvaliacao}")
    public ResponseEntity<List<QuestaoResponseDto>> listarTodasPorAvaliacao(
            @PathVariable Integer idAvaliacao
    ){
        Optional<Avaliacao> avaliacao = avaliacaoService.findById(idAvaliacao);

        if (avaliacao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Questao> questoes = questaoService.listarQuestoesPorAvaliacao(avaliacao.get());
        List<QuestaoResponseDto> responses = QuestaoMapper.toEntity(questoes);

        return ResponseEntity.status(200).body(responses);
    }

    @PostMapping("/{idAvaliacao}/{idQuestao}")
    public ResponseEntity<QuestaoResponseDto> listarPorId(
            @PathVariable Integer idAvaliacao,
            @PathVariable Integer idQuestao
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, idAvaliacao);
        Optional<Questao> questao = questaoService.findById(idComposto);

        if (questao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        QuestaoResponseDto response = QuestaoMapper.toEntity(questao.get());

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{idAvaliacao/{idQuestao}}")
    public ResponseEntity<QuestaoResponseDto> atualizarQuestao(
            @PathVariable Integer idAvaliacao,
            @PathVariable Integer idQuestao,
            @Valid @RequestBody QuestaoAtualizacaoDto request
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, idAvaliacao);
        Optional<Questao> questaoParaAtualizar = questaoService.findById(idComposto);

        if (questaoParaAtualizar.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Questao questao = QuestaoMapper.toEntity(questaoParaAtualizar.get(), idComposto, request);
        Questao questaoAtualizada = questaoService.atualizarQuestao(questao);
        QuestaoResponseDto response = QuestaoMapper.toEntity(questaoAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{idAvaliacao}/{idQuestao}")
    public ResponseEntity deletarQuestao(
            @PathVariable Integer idAvaliacao,
            @PathVariable Integer idQuestao
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, idAvaliacao);

        if (!questaoService.existsById(idComposto)){
            return ResponseEntity.status(404).build();
        }
        
        questaoService.deletarQuestao(idComposto);

        return ResponseEntity.status(200).build();
    }
}
