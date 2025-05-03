package servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioDetalhesDTO;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.util.Optional;

public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontado",username));

        }
        return new UsuarioDetalhesDTO(usuarioOpt.get());
    }


}
