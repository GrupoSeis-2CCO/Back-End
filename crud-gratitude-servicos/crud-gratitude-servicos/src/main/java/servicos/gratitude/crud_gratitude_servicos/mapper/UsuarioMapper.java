package servicos.gratitude.crud_gratitude_servicos.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.*;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {


    static public Usuario toEntity(UsuarioRequestDto request, Cargo cargo){
        Usuario usuario = new Usuario();

        usuario.setCpf(request.getCpf());
        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setData_entrada(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        usuario.setSenha("#Gs" + request.getCpf());
        usuario.setFk_cargo(cargo);

        return usuario;
    }


    static public UsuarioResponseDto toEntity(Usuario usuario){
        UsuarioResponseDto response = new UsuarioResponseDto();

        response.setCpf(usuario.getCpf());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setDataEntrada(usuario.getData_entrada());

        if (usuario.getUltimo_acesso() == null) {
            response.setUltimoAcesso(usuario.getData_entrada());
        } else {
            response.setUltimoAcesso(usuario.getUltimo_acesso());
        }

        response.setCargo(usuario.getFk_cargo().getNome_cargo());

        return response;
    }

    static public List<UsuarioResponseDto> toEntity(List<Usuario> usuarios){
        List<UsuarioResponseDto> responses = new ArrayList<>();

        for (Usuario usuarioDaVez : usuarios) {
            UsuarioResponseDto response = new UsuarioResponseDto();

            response.setCpf(usuarioDaVez.getCpf());
            response.setNome(usuarioDaVez.getNome());
            response.setEmail(usuarioDaVez.getEmail());
            response.setDataEntrada(usuarioDaVez.getData_entrada());
            response.setCargo(usuarioDaVez.getFk_cargo().getNome_cargo());

            if (usuarioDaVez.getUltimo_acesso() == null) {
                response.setUltimoAcesso(usuarioDaVez.getData_entrada());
            } else {
                response.setUltimoAcesso(usuarioDaVez.getUltimo_acesso());
            }

            responses.add(response);
        }

        return responses;
    }

    static public Usuario toEntity(Usuario usuarioAntigo, UsuarioUpdateSenhaDto senha){
        Usuario usuario = new Usuario();

        usuario.setSenha(senha.getSenha());
        usuario.setIdUsuario(usuarioAntigo.getIdUsuario());
        usuario.setCpf(usuarioAntigo.getCpf());
        usuario.setEmail(usuarioAntigo.getEmail());
        usuario.setData_entrada(usuarioAntigo.getData_entrada());
        usuario.setNome(usuarioAntigo.getNome());
        usuario.setFk_cargo(usuarioAntigo.getFk_cargo());
        usuario.setUltimo_acesso(usuarioAntigo.getUltimo_acesso());

        return usuario;
    }
//    public static Usuario of(UsuarioCriacaoDTO dto) {
//        Usuario usuario = new Usuario();
//        usuario.setNome(dto.getNome());
//        usuario.setEmail(dto.getEmail());
//        usuario.setSenha(dto.getSenha());
//        return usuario;
//    }

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
