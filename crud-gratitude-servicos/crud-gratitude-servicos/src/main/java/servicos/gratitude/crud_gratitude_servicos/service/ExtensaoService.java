package servicos.gratitude.crud_gratitude_servicos.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.repository.ExtensaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExtensaoService {

    private final ExtensaoRepository extensaoRepository;

    public ExtensaoService(ExtensaoRepository extensaoRepository) {
        this.extensaoRepository = extensaoRepository;
    }

    public List<Extensao> listarExtensaos(){
        return extensaoRepository.findAll();
    }

    public Boolean extensaoExistById(Integer id){
        return extensaoRepository.existsById(id);
    }

    public Extensao atualizarIcone(Integer id, Extensao extensao){
        extensao.setIdExtensao(id);
        return extensaoRepository.save(extensao);
    }


}
