package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioCriacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioListarDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioLoginDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioTokenDto;

public class UsuarioAutenticationMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDTO){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDTO.getEmail());
        usuario.setNome(usuarioCriacaoDTO.getNome());
        usuario.setSenha(usuarioCriacaoDTO.getSenha());

        return usuario;
    }


    public static Usuario of(UsuarioLoginDto usuarioLoginDTO){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioLoginDTO.getEmail());
        usuario.setSenha(usuarioLoginDTO.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDTO = new UsuarioTokenDto();

        usuarioTokenDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setNome(usuario.getNome());
        usuarioTokenDTO.setToken(token);
        usuarioTokenDTO.setIdCargo(usuario.getFkCargo().getIdCargo()); // Preencha o cargo/tipo aqui

        return usuarioTokenDTO;
    }

    public static UsuarioListarDto of(Usuario usuario){
        UsuarioListarDto usuarioListarDTO = new UsuarioListarDto();

        usuarioListarDTO.setIdUsuario(usuarioListarDTO.getIdUsuario());
        usuarioListarDTO.setEmail(usuarioListarDTO.getEmail());
        usuarioListarDTO.setNome(usuarioListarDTO.getNome());

        return usuarioListarDTO;
    }
}
