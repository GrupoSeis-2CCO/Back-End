package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoAtualizacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.questao.QuestaoRespostaDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.AlternativaMapper;
import servicos.gratitude.crud_gratitude_servicos.mapper.QuestaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AlternativaService;
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
    private final AlternativaService alternativaService;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<QuestaoResponseDto> cadastrarQuestao(
            @Valid @RequestBody QuestaoRequestDto request
    ){
        Optional<Avaliacao> avaliacao = avaliacaoService.findById(request.getFkAvaliacao());

        if (avaliacao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Questao> questoes = questaoService.listarQuestoesPorAvaliacao(avaliacao.get());
        Integer maiorId = 0;
        Integer maiorNumeroQuestao = 0;
        for (Questao questaoDaVez : questoes) {
            if (questaoDaVez.getIdQuestaoComposto().getIdQuestao() > maiorId){
                maiorId = questaoDaVez.getIdQuestaoComposto().getIdQuestao();
            }
            if (questaoDaVez.getNumeroQuestao() > maiorNumeroQuestao){
                maiorNumeroQuestao = questaoDaVez.getNumeroQuestao();
            }
        }
        Integer idQuestao = maiorId + 1;
        Integer numeroQuestao = maiorNumeroQuestao + 1;

        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, request.getFkAvaliacao());

        if (questaoService.existsById(idComposto)){
            return ResponseEntity.status(409).build();
        }

        Questao questao = QuestaoMapper.toEntity(idComposto, maiorNumeroQuestao, request, avaliacao.get());
        Questao questaoCadastrada = questaoService.cadastrarQuestao(questao);
        QuestaoResponseDto response = QuestaoMapper.toEntity(questaoCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/{fkAvaliacao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<QuestaoResponseDto>> listarTodasPorAvaliacao(
            @PathVariable Integer fkAvaliacao
    ){
        Optional<Avaliacao> avaliacao = avaliacaoService.findById(fkAvaliacao);

        if (avaliacao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Questao> questoes = questaoService.listarQuestoesPorAvaliacao(avaliacao.get());
        List<QuestaoResponseDto> responses = QuestaoMapper.toEntity(questoes);

        return ResponseEntity.status(200).body(responses);
    }

    @PostMapping("/{fkAvaliacao}/{idQuestao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<QuestaoResponseDto> listarPorId(
            @PathVariable Integer fkAvaliacao,
            @PathVariable Integer idQuestao
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, fkAvaliacao);
        Optional<Questao> questao = questaoService.findById(idComposto);

        if (questao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        QuestaoResponseDto response = QuestaoMapper.toEntity(questao.get());

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{fkAvaliacao}/{idQuestao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<QuestaoResponseDto> atualizarQuestao(
            @PathVariable Integer fkAvaliacao,
            @PathVariable Integer idQuestao,
            @Valid @RequestBody QuestaoAtualizacaoDto request
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, fkAvaliacao);
        Optional<Questao> questaoParaAtualizar = questaoService.findById(idComposto);

        if (questaoParaAtualizar.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Questao questao = QuestaoMapper.toEntity(questaoParaAtualizar.get(), idComposto, request);
        Questao questaoAtualizada = questaoService.atualizarQuestao(questao);
        QuestaoResponseDto response = QuestaoMapper.toEntity(questaoAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("definir-resposta/{fkAvaliacao}/{idQuestao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<QuestaoResponseDto> definirResposta(
            @Valid @RequestBody QuestaoRespostaDto update,
            @PathVariable Integer fkAvaliacao,
            @PathVariable Integer idQuestao
    ){
        QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(idQuestao, fkAvaliacao);
        Optional<Questao> questao = questaoService.findById(idQuestaoComposto);

        if (questao.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        AlternativaCompoundKey idAlternativaComposto = AlternativaMapper.toEntity(update.getFkAlternativa(), idQuestaoComposto);
        Optional<Alternativa> alternativa = alternativaService.findById(idAlternativaComposto);

        if (alternativa.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        questao.get().setFkAlternativaCorreta(alternativa.get());
        Questao questaoAtualizada = questaoService.atualizarQuestao(questao.get());
        QuestaoResponseDto response = QuestaoMapper.toEntity(questaoAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{fkAvaliacao}/{idQuestao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity deletarQuestao(
            @PathVariable Integer fkAvaliacao,
            @PathVariable Integer idQuestao
    ){
        QuestaoCompoundKey idComposto = QuestaoMapper.toEntity(idQuestao, fkAvaliacao);

        if (!questaoService.existsById(idComposto)){
            return ResponseEntity.status(404).build();
        }

        questaoService.deletarQuestao(idComposto);

        return ResponseEntity.status(200).build();
    }
}
