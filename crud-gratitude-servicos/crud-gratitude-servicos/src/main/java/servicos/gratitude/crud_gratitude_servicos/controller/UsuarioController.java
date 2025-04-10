package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.mapper.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

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
        if(service.existsByEmailAndCpf(request)){
            return ResponseEntity.status(409).build();
        }

        UsuarioMapper mapper = new UsuarioMapper();

        Usuario usuarioNovo = mapper.toEntity(request);
        service.cadastrarUsuario(usuarioNovo);
        UsuarioResponseDto response = mapper.toEntity(usuarioNovo);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(){

        List<Usuario> usuarios = service.listarUsuarios();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<UsuarioResponseDto> responses = new ArrayList<>();
        UsuarioMapper mapper = new UsuarioMapper();

        for(Usuario usuario : usuarios){
            responses.add(mapper.toEntity(usuario));
        }
        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarSenha(
            @RequestParam String senha,
            @PathVariable Integer id
    ){
        if(service.existsById(id)){
            return ResponseEntity.status(404).build();
        }
        service.atualizarSenha(senha, id);
        return ResponseEntity.status(200).build();
    }

}
