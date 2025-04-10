package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(
            @RequestBody @Valid UsuarioRequestDto request
            ){
        return null;
    }
}
