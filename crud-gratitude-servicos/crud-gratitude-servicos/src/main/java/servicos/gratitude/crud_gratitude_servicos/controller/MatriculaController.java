package servicos.gratitude.crud_gratitude_servicos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.MatriculaService;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final UsuarioService usuarioService;
    private final CursoService cursoService;
}
