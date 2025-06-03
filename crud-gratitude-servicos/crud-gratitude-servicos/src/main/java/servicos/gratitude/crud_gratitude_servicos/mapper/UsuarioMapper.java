package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioCriacaoDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario.*;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioListarDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioLoginDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.autenticacao.UsuarioTokenDto;

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
        usuario.setDataEntrada(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
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

        if (usuario.getUltimoAcesso() == null) {
            response.setUltimoAcesso(usuario.getDataEntrada());
        } else {
            response.setUltimoAcesso(usuario.getUltimoAcesso());
        }

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

            if (usuarioDaVez.getUltimoAcesso() == null) {
                response.setUltimoAcesso(usuarioDaVez.getDataEntrada());
            } else {
                response.setUltimoAcesso(usuarioDaVez.getUltimoAcesso());
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
        usuario.setDataEntrada(usuarioAntigo.getDataEntrada());
        usuario.setNome(usuarioAntigo.getNome());
        usuario.setFkCargo(usuarioAntigo.getFkCargo());
        usuario.setUltimoAcesso(usuarioAntigo.getUltimoAcesso());

        return usuario;
    }
//    public static Usuario of(UsuarioCriacaoDto dto) {
//        Usuario usuario = new Usuario();
//        usuario.setNome(dto.getNome());
//        usuario.setEmail(dto.getEmail());
//        usuario.setSenha(dto.getSenha());
//        return usuario;
//    }

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

        usuarioTokenDTO.setIdUsuario(usuarioTokenDTO.getIdUsuario());
        usuarioTokenDTO.setEmail(usuarioTokenDTO.getEmail());
        usuarioTokenDTO.setNome(usuarioTokenDTO.getNome());
        usuarioTokenDTO.setToken(token);

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
