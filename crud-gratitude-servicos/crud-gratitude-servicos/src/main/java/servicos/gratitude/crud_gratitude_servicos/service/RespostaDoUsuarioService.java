package servicos.gratitude.crud_gratitude_servicos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.RespostaDoUsuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.RespostaDoUsuarioCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.RespostaDoUsuarioRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RespostaDoUsuarioService {
    private final RespostaDoUsuarioRepository respostaDoUsuarioRepository;

    public RespostaDoUsuario cadastrarRespostaDoUsuario(RespostaDoUsuario respostaDoUsuario){
        return respostaDoUsuarioRepository.save(respostaDoUsuario);
    }

    public List<RespostaDoUsuario> listarRespostasDosUsuarios(){
        return respostaDoUsuarioRepository.findAll();
    }

    public Boolean existsById(RespostaDoUsuarioCompoundKey idRespostaDoUsuarioComposto){
        return respostaDoUsuarioRepository.existsById(idRespostaDoUsuarioComposto);
    }
}
