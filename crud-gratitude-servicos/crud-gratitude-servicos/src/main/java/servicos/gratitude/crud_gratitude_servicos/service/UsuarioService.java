package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
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
}
