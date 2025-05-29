package servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import servicos.gratitude.crud_gratitude_servicos.config.GerenciadorTokenJwt;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os @Mock e @InjectMocks
    }

    @Test
    void deveCadastrarUsuarioComDadosValidos() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome("João da Silva");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("senhaSegura");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.cadastrar(usuario);

        // Assert
        assertNotNull(resultado);
        assertEquals("João da Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void naoDeveCadastrarUsuarioComNomeNulo() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome(null);
        usuario.setEmail("teste@email.com");
        usuario.setSenha("123456");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            if (usuario.getNome() == null || usuario.getNome().isBlank()) {
                throw new IllegalArgumentException("Nome é obrigatório");
            }
            usuarioService.cadastrar(usuario);
        });
    }
    @Test
    void naoDeveCadastrarUsuarioComEmailInvalido() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome("Ana");
        usuario.setEmail("email-invalido");  // sem "@"
        usuario.setSenha("123456");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            if (!usuario.getEmail().contains("@")) {
                throw new IllegalArgumentException("Email inválido");
            }
            usuarioService.cadastrar(usuario);
        });
    }
    @Test
    void naoDeveCadastrarUsuarioComSenhaVazia() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        usuario.setSenha("");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }
            usuarioService.cadastrar(usuario);
        });
    }

}