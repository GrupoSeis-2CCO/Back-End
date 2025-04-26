package servicos.gratitude.crud_gratitude_servicos.entity.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.UsuarioUpdateSenhaDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    static public Usuario toEntity(UsuarioRequestDto request, Cargo cargo){
        Usuario usuario = new Usuario();

        usuario.setCpf(request.getCpf());
        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setDataEntrada(LocalDate.now());
        usuario.setSenha("#Gs" + request.getCpf());
        usuario.setFkCargo(cargo);

        return usuario;
    }

    static public UsuarioResponseDto toEntity(Usuario usuario){
        UsuarioResponseDto response = new UsuarioResponseDto();

        response.setCpf(usuario.getCpf());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setDataEntrada(usuario.getDataEntrada());
        response.setCargo(usuario.getFkCargo().getNomeCargo());

        return response;
    }

    static public List<UsuarioResponseDto> toEntity(List<Usuario> usuarios){
        List<UsuarioResponseDto> responses = new ArrayList<>();

        for (Usuario usuarioDaVez : usuarios) {
            UsuarioResponseDto response = new UsuarioResponseDto();

            response.setCpf(usuarioDaVez.getCpf());
            response.setNome(usuarioDaVez.getNome());
            response.setEmail(usuarioDaVez.getEmail());
            response.setDataEntrada(usuarioDaVez.getDataEntrada());
            response.setCargo(usuarioDaVez.getFkCargo().getNomeCargo());

            responses.add(response);
        }

        return responses;
    }

    static public Usuario toEntity(Integer id, UsuarioUpdateDto update){
        Usuario usuario = new Usuario();

        usuario.setNome(update.getNome());
        usuario.setEmail(update.getEmail());
        usuario.setIdUsuario(id);

        return usuario;
    }

    static public Usuario toEntity(Integer id, UsuarioUpdateSenhaDto senha){
        Usuario usuario = new Usuario();

        usuario.setSenha(senha.getSenha());
        usuario.setIdUsuario(id);

        return usuario;
    }
}
