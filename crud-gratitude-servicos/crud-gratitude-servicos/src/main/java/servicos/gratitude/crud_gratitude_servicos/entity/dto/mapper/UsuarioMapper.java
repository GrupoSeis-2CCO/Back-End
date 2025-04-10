package servicos.gratitude.crud_gratitude_servicos.entity.dto.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.UsuarioResponseDto;

public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDto request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setCpf(request.getCpf());
        usuario.setNome(request.getNome());
        usuario.setCargo(request.getCargo());

        usuario.setSenha("#Gs"+ request.getCpf());
        return usuario;

    }

    public UsuarioResponseDto toEntity(Usuario usuario) {
        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setEmail(usuario.getEmail());
        response.setNome(usuario.getNome());
        response.setCargo(usuario.getCargo());
        response.setId(usuario.getIdUsuario());

        return response;

    }


}
