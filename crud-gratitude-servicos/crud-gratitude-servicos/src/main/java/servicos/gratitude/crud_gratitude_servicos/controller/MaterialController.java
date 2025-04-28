package servicos.gratitude.crud_gratitude_servicos.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.service.ExtensaoService;
import servicos.gratitude.crud_gratitude_servicos.service.MaterialService;

import java.util.Optional;

@RestController
@RequestMapping("/materiais ")
public class MaterialController {

private final ExtensaoService extensaoService;
private final MaterialService materialService;

    public MaterialController(ExtensaoService extensaoService, MaterialService materialService) {
        this.extensaoService = extensaoService;
        this.materialService = materialService;
    }
}
