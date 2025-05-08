package servicos.gratitude.crud_gratitude_servicos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicos.gratitude.crud_gratitude_servicos.service.AlternativaService;
import servicos.gratitude.crud_gratitude_servicos.service.QuestaoService;

@RestController
@RequestMapping("/alternativas")
public class AlternativaController {

    private final AlternativaService alternativaService;
    private final QuestaoService questaoService;

    public AlternativaController(AlternativaService alternativaService, QuestaoService questaoService) {
        this.alternativaService = alternativaService;
        this.questaoService = questaoService;
    }
}
