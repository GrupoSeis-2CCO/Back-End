package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.AlternativaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.alternativa.AlternativaUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.AlternativaMapper;
import servicos.gratitude.crud_gratitude_servicos.mapper.QuestaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.AlternativaService;
import servicos.gratitude.crud_gratitude_servicos.service.QuestaoService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alternativas")
public class AlternativaController {
    private final AlternativaService alternativaService;
    private final QuestaoService questaoService;

    @PostMapping
    public ResponseEntity<AlternativaResponseDto> cadastrarAlternativa(
            @Valid @RequestBody AlternativaRequestDto request
    ) {
       QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(request.getFkQuestao(), request.getFkAvaliacao());
        Optional<Questao> questao = questaoService.findById(idQuestaoComposto);

        if (questao.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        List<Alternativa> alternativas = alternativaService.listarAlternativasPorQuestao(questao.get());
        Integer maiorId = 0;
        Integer maiorOrdem = 0;

        for (Alternativa alternativaDaVez : alternativas) {
            if (alternativaDaVez.getAlternativaChaveComposta().getIdAlternativa() > maiorId) {
                maiorId = alternativaDaVez.getAlternativaChaveComposta().getIdAlternativa();
            }

            if (alternativaDaVez.getOrdem() > maiorOrdem) {
                maiorOrdem = alternativaDaVez.getOrdem();
            }
        }
        Integer idAlternativa = maiorId + 1;
        Integer ordem = maiorOrdem + 1;

        AlternativaCompoundKey idAlternativaComposto = AlternativaMapper.toEntity(idAlternativa, idQuestaoComposto);

        Alternativa alternativa = AlternativaMapper.toEntity(request, idAlternativaComposto, ordem, questao.get());
        Alternativa alternativaCadastrada = alternativaService.cadastrarAlternativa(alternativa);
        AlternativaResponseDto response = AlternativaMapper.toEntity(alternativaCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{fkQuestao}/{fkAvaliacao}")
    public ResponseEntity<List<AlternativaResponseDto>> listarPorQuestao(
            @PathVariable Integer fkQuestao,
            @PathVariable Integer fkAvaliacao
    ) {
        QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(fkQuestao, fkAvaliacao);
        Optional<Questao> questao = questaoService.findById(idQuestaoComposto);

        if (questao.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        List<Alternativa> alternativas = alternativaService.listarAlternativasPorQuestao(questao.get());

        if (alternativas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<AlternativaResponseDto> responses = AlternativaMapper.toEntity(alternativas);

        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/{idAlternativa}/{fkQuestao}/{fkAvaliacao}")
    public ResponseEntity<AlternativaResponseDto> atualizarAlternativa(
            @Valid @RequestBody AlternativaUpdateDto update,
            @PathVariable Integer idAlternativa,
            @PathVariable Integer fkQuestao,
            @PathVariable Integer fkAvaliacao
    ) {
        QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(fkQuestao, fkAvaliacao);
        Optional<Questao> questao = questaoService.findById(idQuestaoComposto);

        if (questao.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        AlternativaCompoundKey idAlternativaComposto = AlternativaMapper.toEntity(idAlternativa, idQuestaoComposto);
        Optional<Alternativa> alternativaAntiga = alternativaService.findById(idAlternativaComposto);

        if (alternativaAntiga.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Alternativa alternativaAtualizada = AlternativaMapper.toEntity(alternativaAntiga.get(), update);
        AlternativaResponseDto response = AlternativaMapper.toEntity(alternativaAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{idAlternativa}/{fkQuestao}/{fkAvaliacao}")
    public ResponseEntity deletarAlternativa(
            @PathVariable Integer idAlternativa,
            @PathVariable Integer fkQuestao,
            @PathVariable Integer fkAvaliacao
    ) {
        QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(fkQuestao, fkAvaliacao);
        Optional<Questao> questao = questaoService.findById(idQuestaoComposto);

        if (questao.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        AlternativaCompoundKey idAlternativaComposto = AlternativaMapper.toEntity(idAlternativa, idQuestaoComposto);
        Optional<Alternativa> alternativaAntiga = alternativaService.findById(idAlternativaComposto);

        if (alternativaAntiga.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        alternativaService.deletarAlternativa(idAlternativaComposto);

        return ResponseEntity.status(200).build();
    }
}