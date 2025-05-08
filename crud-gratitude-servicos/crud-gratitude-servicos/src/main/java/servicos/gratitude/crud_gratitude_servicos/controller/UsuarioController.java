package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.*;
import servicos.gratitude.crud_gratitude_servicos.mapper.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CargoService;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService.UsuarioService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuários", description = "Gerencia todas as operações relacionadas aos usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CargoService cargoService;
    private final CursoService cursoService;

    public UsuarioController(UsuarioService usuarioService, CargoService cargoService, CursoService cursoService) {
        this.usuarioService = usuarioService;
        this.cargoService = cargoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Cadastrar Usuário", description = "Cadastra um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(
            @Parameter(description = "Dados do usuário a ser cadastrado", required = true)
            @Valid @RequestBody UsuarioRequestDto request
    ) {
        Optional<Cargo> cargoUsuario = cargoService.findById(request.getIdCargo());

        if (cargoUsuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Usuario usuario = UsuarioMapper.toEntity(request, cargoUsuario.get());
        Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Listar Usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado", content = @Content)
    })
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<UsuarioResponseDto> responses = UsuarioMapper.toEntity(usuarios);

        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Obtém detalhes de um usuário específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDto> verUsuario(
            @Parameter(description = "ID do usuário a ser buscado", required = true)
            @PathVariable Integer id
    ) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        return usuario.map(value -> ResponseEntity.status(200).body(UsuarioMapper.toEntity(value)))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping("/novaSenha/{id}")
    @Operation(summary = "Atualizar Senha do Usuário", description = "Atualiza a senha de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDto> atualizarSenhaUsuario(
            @Parameter(description = "Nova senha do usuário", required = true)
            @Valid @RequestBody UsuarioUpdateSenhaDto senha,
            @Parameter(description = "ID do usuário a ser atualizado", required = true)
            @PathVariable Integer id
    ) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Usuario senhaNova = UsuarioMapper.toEntity(usuario.get(), senha);
        Usuario usuarioAtualizado = usuarioService.atualizarDados(senhaNova);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

//    UsuarioController JWT

//    @PostMapping
//    @SecurityRequirement(name = "Bearer")
//    public ResponseEntity<Void> criar (@RequestBody @Valid UsuarioCriacaoDTO usuarioCriacaoDTO){
//        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDTO);
//        this.usuarioService.criar(novoUsuario);
//        return ResponseEntity.status(201).build();
//    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO>login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        final Usuario usuario = UsuarioMapper.of(usuarioLoginDTO);
        UsuarioTokenDTO usuarioTokenDTO = this.usuarioService.autenticar(usuario);
        return ResponseEntity.status(200).body(usuarioTokenDTO);
    }
    public static class UsuarioRespostaLogin {
        private final String token;

        public UsuarioRespostaLogin(String token) {
            this.token = token;
        }

        // Getters
        public String getToken() {
            return token;
        }
    }

//    @GetMapping
//    @SecurityRequirement(name = "Bearer")
//    public ResponseEntity<List<UsuarioListarDTO>> listarTodos(){
//        List<UsuarioListarDTO> usuariosEncontrados = this.usuarioService.listarTodos();
//
//        if (usuariosEncontrados.isEmpty()){
//             return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(usuariosEncontrados);
//    }
}