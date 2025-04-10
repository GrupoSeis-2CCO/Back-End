package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

}
