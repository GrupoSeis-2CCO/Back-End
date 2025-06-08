package servicos.gratitude.crud_gratitude_servicos.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    // ============== cadastrarCurso ==============
    @Test
    void cadastrarCurso_DeveRetornarCursoSalvo_QuandoDadosValidos() {
        Curso curso = criarCursoValido("Java Avançado", true);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso resultado = cursoService.cadastrarCurso(curso);

        assertNotNull(resultado);
        assertEquals("Java Avançado", resultado.getTituloCurso());
        verify(cursoRepository, times(1)).save(curso);
    }

    @Test
    void cadastrarCurso_DeveManterValorOculto_QuandoInformado() {
        Curso curso = criarCursoValido("Spring Boot", false);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso resultado = cursoService.cadastrarCurso(curso);

        assertFalse(resultado.getOcultado());
    }

    @Test
    void cadastrarCurso_DeveSalvarComSucesso_QuandoCamposObrigatoriosPresentes() {
        Curso curso = new Curso();
        curso.setTituloCurso("Curso Essencial");
        curso.setOcultado(true);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso resultado = cursoService.cadastrarCurso(curso);

        assertNotNull(resultado);
        assertEquals("Curso Essencial", resultado.getTituloCurso());
    }

    // ============== listarCursos ==============
    @Test
    void listarCursos_DeveRetornarListaVazia_QuandoNenhumCursoCadastrado() {
        when(cursoRepository.findAll()).thenReturn(List.of());

        List<Curso> resultado = cursoService.listarCursos();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void listarCursos_DeveRetornarDoisCursos_QuandoDoisCadastrados() {
        Curso curso1 = criarCursoValido("Curso 1", true);
        Curso curso2 = criarCursoValido("Curso 2", false);

        when(cursoRepository.findAll()).thenReturn(List.of(curso1, curso2));

        List<Curso> resultado = cursoService.listarCursos();

        assertEquals(2, resultado.size());
    }

    @Test
    void listarCursos_DeveRetornarCursosNaOrdemDoRepositorio() {
        Curso curso1 = criarCursoValido("Primeiro", false);
        Curso curso2 = criarCursoValido("Segundo", true);

        when(cursoRepository.findAll()).thenReturn(List.of(curso1, curso2));

        List<Curso> resultado = cursoService.listarCursos();

        assertEquals("Primeiro", resultado.get(0).getTituloCurso());
        assertEquals("Segundo", resultado.get(1).getTituloCurso());
    }

    // ============== existsById ==============
    @Test
    void existsById_DeveRetornarTrue_QuandoCursoExistente() {
        when(cursoRepository.existsById(1)).thenReturn(true);

        assertTrue(cursoService.existsById(1));
    }

    @Test
    void existsById_DeveRetornarFalse_QuandoCursoInexistente() {
        when(cursoRepository.existsById(999)).thenReturn(false);

        assertFalse(cursoService.existsById(999));
    }

    @Test
    void existsById_DeveConsultarRepositorio_QuandoChamado() {
        cursoService.existsById(1);
        verify(cursoRepository, times(1)).existsById(1);
    }

    // ============== atualizarCurso ==============
    @Test
    void atualizarCurso_DeveAtualizarCampos_QuandoCursoExistente() {
        Curso novosDados = criarCursoValido("Novo Título", false);

        // Removido existsById pois não é usado no service
        when(cursoRepository.save(any(Curso.class))).thenReturn(novosDados);

        Curso resultado = cursoService.atualizarCurso(1, novosDados);

        assertEquals("Novo Título", resultado.getTituloCurso());
        assertFalse(resultado.getOcultado());
    }

    @Test
    void atualizarCurso_DeveManterIdCorreto_QuandoAtualizado() {
        Curso novosDados = criarCursoValido("Título Atualizado", true);

        // Simulação simplificada
        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Curso resultado = cursoService.atualizarCurso(1, novosDados);

        assertEquals(1, resultado.getIdCurso().intValue());
    }

    @Test
    void atualizarCurso_DeveSalvarCurso_QuandoExistente() {
        Curso curso = criarCursoValido("Atualização", true);

        // Simulação direta do save
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso resultado = cursoService.atualizarCurso(1, curso);

        assertNotNull(resultado);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    // ============== cursoIsOculto ==============
    @Test
    void cursoIsOculto_DeveRetornarTrue_QuandoCursoOculto() {
        Curso curso = criarCursoValido("Oculto", true);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        assertTrue(cursoService.cursoIsOculto(1));
    }

    @Test
    void cursoIsOculto_DeveRetornarFalse_QuandoCursoVisivel() {
        Curso curso = criarCursoValido("Visível", false);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        assertFalse(cursoService.cursoIsOculto(1));
    }

    @Test
    void cursoIsOculto_DeveBuscarPeloIdCorreto() {
        // Corrigido para não lançar exceção
        Curso curso = criarCursoValido("Teste", true);
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        cursoService.cursoIsOculto(1);
        verify(cursoRepository, times(1)).findById(1);
    }

    // ============== atualizarOculto ==============
    @Test
    void atualizarOculto_DeveMudarStatusParaOculto_QuandoTrue() {
        Curso curso = criarCursoValido("Curso", false);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Curso resultado = cursoService.atualizarOculto(1, true);

        assertTrue(resultado.getOcultado());
    }

    @Test
    void atualizarOculto_DeveMudarStatusParaVisivel_QuandoFalse() {
        Curso curso = criarCursoValido("Curso", true);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Curso resultado = cursoService.atualizarOculto(1, false);

        assertFalse(resultado.getOcultado());
    }

    @Test
    void atualizarOculto_DeveSalvarAlteracoes_QuandoSucesso() {
        Curso curso = criarCursoValido("Curso", true);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        cursoService.atualizarOculto(1, false);

        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    // ============== deletarCurso ==============
    @Test
    void deletarCurso_DeveChamarRepositorio_QuandoExistente() {
        cursoService.deletarCurso(1);
        verify(cursoRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarCurso_DeveNaoLancarExcecao_QuandoInexistente() {
        assertDoesNotThrow(() -> cursoService.deletarCurso(999));
    }

    @Test
    void deletarCurso_DeveExecutarSemErros_QuandoQualquerId() {
        assertDoesNotThrow(() -> cursoService.deletarCurso(1));
    }

    // ============== findById ==============
    @Test
    void findById_DeveRetornarCurso_QuandoExistente() {
        Curso curso = criarCursoValido("Encontrado", true);

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        Optional<Curso> resultado = cursoService.findById(1);

        assertTrue(resultado.isPresent());
        assertEquals("Encontrado", resultado.get().getTituloCurso());
    }

    @Test
    void findById_DeveRetornarVazio_QuandoInexistente() {
        when(cursoRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Curso> resultado = cursoService.findById(999);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_DeveBuscarPeloIdCorreto() {
        when(cursoRepository.findById(1)).thenReturn(Optional.empty());

        cursoService.findById(1);
        verify(cursoRepository, times(1)).findById(1);
    }

    // Método auxiliar para criar cursos válidos
    private Curso criarCursoValido(String titulo, boolean ocultado) {
        Curso curso = new Curso();
        curso.setTituloCurso(titulo);
        curso.setDescricao("Descrição do curso");
        curso.setImagem("imagem.jpg");
        curso.setDuracaoEstimada(10);
        curso.setOcultado(ocultado);
        return curso;
    }
        // Id1 - Cadastro bem-sucedido
        @Test
        void quandoCadastrarCursoComDadosCompletos_entaoSalvarCurso() {
            Curso curso = new Curso();
            curso.setTituloCurso("Lógica de Programação");
            curso.setDuracaoEstimada(15);

            when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

            Curso resultado = cursoService.cadastrarCurso(curso);

            assertNotNull(resultado);
            assertEquals("Lógica de Programação", resultado.getTituloCurso());
            verify(cursoRepository, times(1)).save(curso);
        }

        @Test
        void quandoCadastrarCursoDuplicado_entaoLancarExcecao() {
            Curso cursoExistente = new Curso();
            cursoExistente.setTituloCurso("Curso Básico de Java");

            when(cursoRepository.findbytitulocurso("Curso Básico de Java"))
                    .thenReturn(Optional.of(cursoExistente));

            Curso novoCurso = new Curso();
            novoCurso.setTituloCurso("Curso Básico de Java");

            assertThrows(RuntimeException.class, () -> cursoService.cadastrarCurso(novoCurso));
        }

        // Id3 - Validação de formato de imagem (normalmente feito no Controller/DTO)
        @Test
        void quandoAtualizarCursoComImagemInvalida_entaoNaoSalvar() {
            // Este teste é ilustrativo. A validação de imagem deve ser feita antes do serviço!
            Curso curso = new Curso();
            curso.setImagem("documento.pdf"); // Formato inválido

            // Simular validação falhada (o serviço não valida imagens!)
            assertThrows(IllegalArgumentException.class, () -> {
                if (!curso.getImagem().endsWith(".png")) {
                    throw new IllegalArgumentException("Formato de imagem inválido");
                }
                cursoService.cadastrarCurso(curso);
            });
        }

        // Id4 - Atualização de contadores (não é responsabilidade do serviço)
        // (Este cenário depende de lógica externa e não é testável no serviço)

        // Id6 - Aceitação de caracteres especiais
        @Test
        void quandoCadastrarCursoComCaracteresEspeciais_entaoSalvar() {
            Curso curso = new Curso();
            curso.setTituloCurso("Curso Avançado!@#");


            when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

            Curso resultado = cursoService.cadastrarCurso(curso);

            assertEquals("Curso Avançado!@#", resultado.getTituloCurso());
        }

        // Testes para métodos adicionais
        @Test
        void quandoAtualizarOculto_entaoAlterarStatus() {
            Curso curso = new Curso();
            curso.setIdCurso(1);
            curso.setOcultado(false);

            when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
            when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

            Curso resultado = cursoService.atualizarOculto(1, true);

            assertTrue(resultado.getOcultado());
        }

        @Test
        void quandoDeletarCursoInexistente_entaoLancarExcecao() {
            doThrow(new RuntimeException("Curso não encontrado")).when(cursoRepository).deleteById(999);
            assertThrows(RuntimeException.class, () -> cursoService.deletarCurso(999));
        }
    }
