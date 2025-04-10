package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Boolean existsByEmailAndCpf(UsuarioRequestDto request) {
        return repository.existsByEmailAndCpf(request.getEmail(), request.getCpf());
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public void cadastrarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public Boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void atualizarSenha(String senha, Integer idUsuario) {
        Usuario usuarioParaAtualizar = repository.findById(idUsuario).get();
        usuarioParaAtualizar.setSenha(senha);
        repository.save(usuarioParaAtualizar);
    }

}
