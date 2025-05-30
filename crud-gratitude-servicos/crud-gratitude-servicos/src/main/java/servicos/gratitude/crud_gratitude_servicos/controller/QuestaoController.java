package servicos.gratitude.crud_gratitude_servicos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicos.gratitude.crud_gratitude_servicos.service.AvaliacaoService;
import servicos.gratitude.crud_gratitude_servicos.service.QuestaoService;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    private final QuestaoService questaoService;
    private final AvaliacaoService avaliacaoService;

    public QuestaoController(QuestaoService questaoService, AvaliacaoService avaliacaoService) {
        this.questaoService = questaoService;
        this.avaliacaoService = avaliacaoService;
    }
}
