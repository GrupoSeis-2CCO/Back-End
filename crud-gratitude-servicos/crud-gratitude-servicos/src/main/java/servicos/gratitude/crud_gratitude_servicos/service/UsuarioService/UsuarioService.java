package servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import servicos.gratitude.crud_gratitude_servicos.config.GerenciadorTokenJwt;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioListarDTO;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioMapper;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioTokenDTO;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id){
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarDados(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarAcesso(Integer id, LocalDateTime ultimoAcesso){
        Optional<Usuario> usuario = findById(id);
        usuario.get().setUltimo_acesso(ultimoAcesso);
        return usuarioRepository.save(usuario.get());
    }

    public List<Usuario> pesquisaPorNome(String nome){
        return usuarioRepository.findByNomeContainsIgnoreCase(nome);
    }


//    Service JWT



    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJWT;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar (Usuario novoUsuario){
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    //    Pendente
    public UsuarioTokenDTO autenticar(Usuario usuario){
        System.out.println("Tentativa de login: " + usuario.getEmail());
        System.out.println("[DEBUG] Senha recebida na requisição: " + usuario.getSenha());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String hashSimulado = encoder.encode(usuario.getSenha());


        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuario.getEmail())
                        .orElseThrow(() -> {
                            System.out.println("Usuário não encontrado: " + usuario.getEmail());
                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não cadastrado");
                        });
        System.out.println("Hash da senha no banco: " + usuarioAutenticado.getSenha());
        boolean senhaConfere = passwordEncoder.matches(usuario.getSenha(), usuarioAutenticado.getSenha());
        System.out.println("[DEBUG] Resultado da comparação: " + senhaConfere);
        System.out.println("Senha recebida: " + usuario.getSenha());
        System.out.println("Hash gerado local: " + hashSimulado);
        System.out.println("Hash do banco: " + usuarioAutenticado.getSenha());
        System.out.println("Validação local: " + encoder.matches(usuario.getSenha(), hashSimulado));

        if (!senhaConfere) {
            System.out.println("[ERRO] Hash não corresponde à senha fornecida");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        if (!passwordEncoder.matches(usuario.getSenha(), usuarioAutenticado.getSenha())) {
            throw new ResponseStatusException(401, "Credenciais inválidas", null);
        }

        final Authentication authentication = new UsernamePasswordAuthenticationToken(
                usuarioAutenticado.getEmail(), null, List.of()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJWT.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }




    public List<UsuarioListarDTO> listarTodos(){
        List<Usuario> usuariosEncontrados = usuarioRepository.findAll();
        return usuariosEncontrados.stream().map(UsuarioMapper::of).toList();
    }

}
