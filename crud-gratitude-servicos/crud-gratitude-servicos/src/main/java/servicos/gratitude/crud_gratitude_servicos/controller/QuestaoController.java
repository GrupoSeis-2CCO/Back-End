package servicos.gratitude.crud_gratitude_servicos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.QuestaoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    private final QuestaoService questaoService;
    private final AvaliacaoService avaliacaoService;

}
