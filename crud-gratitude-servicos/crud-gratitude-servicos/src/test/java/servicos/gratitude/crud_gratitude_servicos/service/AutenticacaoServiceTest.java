package servicos.gratitude.crud_gratitude_servicos.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutenticacaoServiceTest {

    private UsuarioRepository usuarioRepository;
    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        autenticacaoService = new AutenticacaoService(usuarioRepository);
    }

    // Id1 - Login válido
    @Test
    void deveAutenticarComEmailEsenhaValidos() {
        Usuario usuario = new Usuario();
        usuario.setEmail("colaborador01@empresa.com");
        usuario.setSenha("12345");

        when(usuarioRepository.findByEmail("colaborador01@empresa.com"))
                .thenReturn(Optional.of(usuario));

        var userDetails = autenticacaoService.loadUserByUsername("colaborador01@empresa.com");

        assertEquals("colaborador01@empresa.com", userDetails.getUsername());
        assertEquals("12345", userDetails.getPassword());
    }

    // Id2 - Senha incorreta (validação não feita aqui, apenas retorno do usuário)
    @Test
    void deveRetornarUsuarioMesmoComSenhaErrada() {
        Usuario usuario = new Usuario();
        usuario.setEmail("colaborador01@empresa.com");
        usuario.setSenha("12345"); // senha correta

        when(usuarioRepository.findByEmail("colaborador01@empresa.com"))
                .thenReturn(Optional.of(usuario));

        var userDetails = autenticacaoService.loadUserByUsername("colaborador01@empresa.com");

        // Controller deve verificar a senha, não o service
        assertEquals("12345", userDetails.getPassword());
    }

    // Id3 - E-mail não cadastrado
    @Test
    void deveLancarExcecaoQuandoEmailNaoExistir() {
        when(usuarioRepository.findByEmail("fake@empresa.com"))
                .thenReturn(Optional.empty());

        var ex = assertThrows(UsernameNotFoundException.class, () ->
                autenticacaoService.loadUserByUsername("fake@empresa.com"));

        assertEquals("Usuário não encontrado", ex.getMessage());
    }

    // Id4 - Campos vazios
    @Test
    void deveLancarExcecaoSeEmailForVazio() {
        var ex = assertThrows(IllegalArgumentException.class, () ->
                autenticacaoService.loadUserByUsername("  "));

        assertEquals("Preencha o campo de e-mail", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeSenhaForVazia() {
        var ex = assertThrows(IllegalArgumentException.class, () ->
                autenticacaoService.validarSenha(""));

        assertEquals("Preencha a senha", ex.getMessage());
    }

    // Id5 - Formato de e-mail inválido
    @Test
    void deveLancarExcecaoParaEmailInvalido() {
        var ex = assertThrows(IllegalArgumentException.class, () ->
                autenticacaoService.loadUserByUsername("usuario.email.com"));

        assertEquals("Formato de e-mail inválido", ex.getMessage());
    }

    // Id6 - E-mail com espaços extras
    @Test
    void deveAceitarEmailComEspacosExtras() {
        Usuario usuario = new Usuario();
        usuario.setEmail("colaborador01@empresa.com");
        usuario.setSenha("12345");

        when(usuarioRepository.findByEmail("colaborador01@empresa.com"))
                .thenReturn(Optional.of(usuario));

        var userDetails = autenticacaoService.loadUserByUsername("  colaborador01@empresa.com  ");

        assertEquals("colaborador01@empresa.com", userDetails.getUsername());
    }

    // Id7 - Senha com menos de 4 caracteres
    @Test
    void deveLancarExcecaoSeSenhaForMuitoCurta() {
        var ex = assertThrows(IllegalArgumentException.class, () ->
                autenticacaoService.validarSenha("12"));

        assertEquals("A senha deve ter pelo menos 4 caracteres", ex.getMessage());
    }

    @Test
    void deveAceitarSenhaValida() {
        assertDoesNotThrow(() -> autenticacaoService.validarSenha("12345"));
    }
}
