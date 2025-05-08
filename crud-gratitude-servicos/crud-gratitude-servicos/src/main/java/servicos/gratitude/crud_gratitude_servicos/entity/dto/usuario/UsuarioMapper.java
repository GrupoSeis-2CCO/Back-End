package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDTO usuarioCriacaoDTO){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDTO.getEmail());
        usuario.setNome(usuarioCriacaoDTO.getNome());
        usuario.setSenha(usuarioCriacaoDTO.getSenha());

        return usuario;
    }


    public static Usuario of(UsuarioLoginDTO usuarioLoginDTO){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioLoginDTO.getEmail());
        usuario.setSenha(usuarioLoginDTO.getSenha());

        return usuario;
    }

    public static UsuarioTokenDTO  of(Usuario usuario, String token){
        UsuarioTokenDTO  usuarioTokenDTO = new UsuarioTokenDTO();

        usuarioTokenDTO.setIdUsuario(usuarioTokenDTO.getIdUsuario());
        usuarioTokenDTO.setEmail(usuarioTokenDTO.getEmail());
        usuarioTokenDTO.setNome(usuarioTokenDTO.getNome());
        usuarioTokenDTO.setToken(token);

        return usuarioTokenDTO;
    }

    public static UsuarioListarDTO of(Usuario usuario){
        UsuarioListarDTO usuarioListarDTO = new UsuarioListarDTO();

        usuarioListarDTO.setIdUsuario(usuarioListarDTO.getIdUsuario());
        usuarioListarDTO.setEmail(usuarioListarDTO.getEmail());
        usuarioListarDTO.setNome(usuarioListarDTO.getNome());

        return usuarioListarDTO;
    }
}
