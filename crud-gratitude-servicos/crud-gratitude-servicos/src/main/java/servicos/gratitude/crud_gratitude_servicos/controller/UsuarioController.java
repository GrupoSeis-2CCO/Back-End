package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.*;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioLoginDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioTokenDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CargoService;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Tag(name = "Usuários", description = "Gerencia todas as operações relacionadas aos usuários")
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final CargoService cargoService;
    private final CursoService cursoService;

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

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Deletar Usuário", description = "Remove um usuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário a ser deletado", required = true)
            @PathVariable Integer id
    ) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/acesso/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Atualizar Último Acesso", description = "Atualiza o último acesso do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDto> atualizarAcesso(
            @Parameter(description = "ID do usuário a ser atualizado", required = true)
            @PathVariable Integer id
    ) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        LocalDateTime horarioAcesso = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Usuario usuarioAtualizado = usuarioService.atualizarAcesso(id, horarioAcesso);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/pesquisa-por-nome")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Pesquisar Usuários por Nome", description = "Retorna usuários cujo nome corresponda ao parâmetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado", content = @Content)
    })
    public ResponseEntity<List<UsuarioResponseDto>> pesquisarPorNome(
            @Parameter(description = "Nome do usuário para pesquisa", required = true)
            @RequestParam String nome
    ) {
        List<Usuario> usuarios = usuarioService.pesquisaPorNome(nome);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(UsuarioMapper.toEntity(usuarios));
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
    @SecurityRequirement(name = "Bearer")
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
    @SecurityRequirement(name = "Bearer")
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
    ){
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Usuario senhaNova = UsuarioMapper.toEntity(usuario.get(), senha);
        Usuario usuarioAtualizado = usuarioService.atualizarDados(senhaNova);
        UsuarioResponseDto response = UsuarioMapper.toEntity(usuarioAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/login")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Autenticação de Usuário", description = "Autentica um usuário e retorna um token de acesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioTokenDto.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content)
    })
    public ResponseEntity<UsuarioTokenDto> login(
            @Parameter(description = "Credenciais do usuário", required = true)
            @RequestBody UsuarioLoginDto usuarioLoginDTO
    ) {
        final Usuario usuario = UsuarioMapper.of(usuarioLoginDTO);
        UsuarioTokenDto usuarioTokenDTO = usuarioService.autenticar(usuario);
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
}

//    UsuarioController JWT

//    @PostMapping
//    @SecurityRequirement(name = "Bearer")
//    public ResponseEntity<Void> criar (@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDTO){
//        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDTO);
//        this.usuarioService.criar(novoUsuario);
//        return ResponseEntity.status(201).build();
//    }

