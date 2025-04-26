package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioUpdateSenhaDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CargoService;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CargoService cargoService;

    public UsuarioController(UsuarioService usuarioService, CargoService cargoService) {
        this.usuarioService = usuarioService;
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(
            @Valid @RequestBody UsuarioRequestDto request
    ){
        Optional<Cargo> cargoUsuario = cargoService.findById(request.getIdCargo());

        if (cargoUsuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Usuario usuario = UsuarioMapper.toEntity(request, cargoUsuario.get());
        Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        if (usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<UsuarioResponseDto> responses = UsuarioMapper.toEntity(usuarios);

        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> verUsuario(
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isPresent()){
            UsuarioResponseDto response = UsuarioMapper.toEntity(usuario.get());
            return ResponseEntity.status(200).body(response);
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/novosDados/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarDadosUsuario(
            @Valid @RequestBody UsuarioUpdateDto update,
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Usuario dadosNovos = UsuarioMapper.toEntity(id, update);
        Usuario usuarioAtualizado = usuarioService.atualizarDados(dadosNovos);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/novaSenha/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarSenhaUsuario(
            @Valid @RequestBody UsuarioUpdateSenhaDto senha,
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Usuario senhaNova = UsuarioMapper.toEntity(id, senha);
        Usuario usuarioAtualizado = usuarioService.atualizarDados(senhaNova);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        usuarioService.deletarUsuario(id);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("/acesso/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarAcesso(
            @PathVariable Integer id
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if(usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        LocalDate horarioAcesso = LocalDate.now();
        Usuario usuarioAtualizado = usuarioService.atualizarAcesso(id, horarioAcesso);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    // TODO: CONTINUAR POR AQUI
}
