package servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servicos.gratitude.crud_gratitude_servicos.entity.Cargo;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.repository.UsuarioRepository;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.*;

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

    //Testes de cadastrar
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

    @Test
    void naoDeveCadastrarUsuarioComCpfNulo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Marina");
        usuario.setEmail("marina@email.com");
        usuario.setSenha("senha123");
        usuario.setCpf(null); // CPF nulo

        assertThrows(IllegalArgumentException.class, () -> {
            if (usuario.getCpf() == null || usuario.getCpf().isBlank()) {
                throw new IllegalArgumentException("CPF é obrigatório");
            }
            usuarioService.cadastrar(usuario);
        });
    }

    @Test
    void naoDeveCadastrarUsuarioSemCargo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Joana");
        usuario.setEmail("joana@email.com");
        usuario.setSenha("senha123");
        usuario.setCpf("12345678900");
        usuario.setFk_cargo(null); // Cargo nulo

        assertThrows(IllegalArgumentException.class, () -> {
            if (usuario.getFk_cargo() == null) {
                throw new IllegalArgumentException("Cargo é obrigatório");
            }
            usuarioService.cadastrar(usuario);
        });
    }

    @Test
    void deveCadastrarUsuarioComTodosOsCamposValidos() {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucas");
        usuario.setEmail("lucas@email.com");
        usuario.setSenha("senhaSegura123");
        usuario.setCpf("12345678901");
        usuario.setData_entrada(LocalDateTime.now());
        usuario.setUltimo_acesso(LocalDateTime.now());

        Cargo cargo = new Cargo();
        usuario.setFk_cargo(cargo);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.cadastrar(usuario);

        assertNotNull(resultado);
        assertEquals("Lucas", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void naoDeveSalvarUsuarioSeValidacaoFalhar() {
        Usuario usuario = new Usuario();
        usuario.setNome(""); // Nome inválido
        usuario.setEmail("teste@email.com");
        usuario.setSenha("123");

        assertThrows(IllegalArgumentException.class, () -> {
            if (usuario.getNome() == null || usuario.getNome().isBlank()) {
                throw new IllegalArgumentException("Nome é obrigatório");
            }
            usuarioService.cadastrar(usuario);
        });

        verify(usuarioRepository, never()).save(any());
    }


    // Testes de listar usuarios
    @Test
    void deveRetornarListaDeUsuariosQuandoExistiremUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setNome("João");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Maria");

        List<Usuario> usuarios = List.of(usuario1, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremUsuarios() {
        // Arrange
        when(usuarioRepository.findAll()).thenReturn(List.of());

        // Act
        List<Usuario> resultado = usuarioService.listar();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deveLancarExcecaoQuandoAcessoAoRepositorioFalhar() {
        // Arrange
        when(usuarioRepository.findAll()).thenThrow(new RuntimeException("Erro ao acessar banco"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.listar();
        });

        assertEquals("Erro ao acessar banco", exception.getMessage());
        verify(usuarioRepository, times(1)).findAll();
    }

    //Testes Listar por ID
    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Pedro");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> resultado = usuarioService.findById(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Pedro", resultado.get().getNome());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void deveRetornarOptionalVazioQuandoIdNaoExistir() {
        // Arrange
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> resultado = usuarioService.findById(99);

        // Assert
        assertFalse(resultado.isPresent());
        verify(usuarioRepository, times(1)).findById(99);
    }

    @Test
    void deveLancarExcecaoQuandoRepositorioFalha() {
        // Arrange
        when(usuarioRepository.findById(anyInt())).thenThrow(new RuntimeException("Erro ao procurar id"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.findById(1);
        });

        assertEquals("Erro ao procurar id", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1);
    }


//Teste de atualizar
    @Test
    void deveAtualizarDadosDoUsuarioComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Antônio");
        usuario.setEmail("antonio@email.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.atualizarDados(usuario);

        // Assert
        assertNotNull(resultado);
        assertEquals("Antônio", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }
    @Test
    void deveAtualizarDadosDoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Nome Atualizado");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.atualizarDados(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoFalharAoAtualizarUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        usuario.setNome("Carlos");

        when(usuarioRepository.save(usuario)).thenThrow(new RuntimeException("Erro ao atualizar"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.atualizarDados(usuario);
        });

        assertEquals("Erro ao atualizar", exception.getMessage());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    //delete

    @Test
    void deveDeletarUsuarioQuandoIdExistir() {
        // Arrange
        Integer id = 1;
        when(usuarioRepository.existsById(id)).thenReturn(true);

        // Act
        usuarioService.deletarUsuario(id);

        // Assert
        verify(usuarioRepository).deleteById(id);
    }

    //Atualizar acesso
    @Test
    void deveAtualizarUltimoAcessoQuandoUsuarioExistir() {
        Integer idUsuario = 1;
        LocalDateTime novoAcesso = LocalDateTime.now();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setUltimo_acesso(LocalDateTime.now().minusDays(1));

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuarioAtualizado = usuarioService.atualizarAcesso(idUsuario, novoAcesso);

        assertNotNull(usuarioAtualizado);
        assertEquals(novoAcesso, usuarioAtualizado.getUltimo_acesso());
        verify(usuarioRepository).findById(idUsuario);
        verify(usuarioRepository).save(usuario);
    }
    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Integer idUsuario = 999;
        LocalDateTime novoAcesso = LocalDateTime.now();

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            usuarioService.atualizarAcesso(idUsuario, novoAcesso);
        });

        verify(usuarioRepository).findById(idUsuario);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoSalvarFalhar() {
        Integer idUsuario = 1;
        LocalDateTime novoAcesso = LocalDateTime.now();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setUltimo_acesso(LocalDateTime.now().minusDays(1));

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.atualizarAcesso(idUsuario, novoAcesso);
        });

        assertEquals("Erro ao salvar", exception.getMessage());
        verify(usuarioRepository).findById(idUsuario);
        verify(usuarioRepository).save(usuario);
    }
    //Pesquisar por nome

    @Test
    void pesquisaPorNome_retornaListaVazia_quandoNomeNaoExiste() {
        String nome = "inexistente";

        when(usuarioRepository.findByNomeContainsIgnoreCase(nome)).thenReturn(Collections.emptyList());

        List<Usuario> resultado = usuarioService.pesquisaPorNome(nome);

        assertTrue(resultado.isEmpty());
        verify(usuarioRepository).findByNomeContainsIgnoreCase(nome);
    }

    @Test
    void pesquisaPorNome_trataNomeNulo_ouVazio() {
        String nomeNulo = null;
        String nomeVazio = "";

        when(usuarioRepository.findByNomeContainsIgnoreCase(nomeNulo)).thenReturn(Collections.emptyList());
        when(usuarioRepository.findByNomeContainsIgnoreCase(nomeVazio)).thenReturn(Collections.emptyList());

        List<Usuario> resultadoNulo = usuarioService.pesquisaPorNome(nomeNulo);
        List<Usuario> resultadoVazio = usuarioService.pesquisaPorNome(nomeVazio);

        assertTrue(resultadoNulo.isEmpty());
        assertTrue(resultadoVazio.isEmpty());

        verify(usuarioRepository).findByNomeContainsIgnoreCase(nomeNulo);
        verify(usuarioRepository).findByNomeContainsIgnoreCase(nomeVazio);
    }

    @Test
    void pesquisaPorNome_deveRetornarUsuariosComNomeContendoString() {
        // Arrange
        List<Usuario> usuarios = new ArrayList<>();

        Usuario u1 = new Usuario();
        u1.setIdUsuario(1);
        u1.setNome("Joao Silva");

        Usuario u2 = new Usuario();
        u2.setIdUsuario(2);
        u2.setNome("Joao Pedro");

        usuarios.add(u1);
        usuarios.add(u2);

        String nomeBusca = "joao";

        when(usuarioRepository.findByNomeContainsIgnoreCase(nomeBusca)).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.pesquisaPorNome(nomeBusca);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(u -> u.getNome().toLowerCase().contains(nomeBusca.toLowerCase())));
    }

}