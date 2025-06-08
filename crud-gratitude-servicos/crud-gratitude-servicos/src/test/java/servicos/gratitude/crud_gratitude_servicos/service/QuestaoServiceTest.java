package servicos.gratitude.crud_gratitude_servicos.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.entity.Questao;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.QuestaoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.repository.QuestaoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestaoServiceTest {

    @Mock
    private QuestaoRepository questaoRepository;

    @InjectMocks
    private QuestaoService questaoService;

    private Avaliacao criarAvaliacaoValida(Long id) {
        Avaliacao mockAvaliacao = mock(Avaliacao.class);
        when(mockAvaliacao.getIdAvaliacao()).thenReturn(id);
        return mockAvaliacao;
    }

    private Questao criarQuestaoValida(Avaliacao avaliacao, Integer numeroQuestao, String enunciado) {
        Questao questao = new Questao();
        QuestaoCompoundKey key = new QuestaoCompoundKey();
        key.setFkAvaliacao(avaliacao.getIdAvaliacao());
        key.setNumeroQuestao(numeroQuestao);

        questao.setIdQuestaoComposto(key);
        questao.setAvaliacao(avaliacao);
        questao.setNumeroQuestao(numeroQuestao);
        questao.setEnunciado(enunciado);
        return questao;
    }

    // ============== cadastrarQuestao ==============
    @Test
    void cadastrarQuestao_DeveRetornarQuestaoSalva_QuandoDadosValidos() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        Questao questaoParaSalvar = criarQuestaoValida(avaliacao, 1, "Qual é a capital da França?");
        when(questaoRepository.save(any(Questao.class))).thenReturn(questaoParaSalvar);

        // Act
        Questao resultado = questaoService.cadastrarQuestao(questaoParaSalvar);

        // Assert
        assertNotNull(resultado);
        assertEquals(questaoParaSalvar.getEnunciado(), resultado.getEnunciado());
        assertEquals(questaoParaSalvar.getNumeroQuestao(), resultado.getNumeroQuestao());
        verify(questaoRepository, times(1)).save(questaoParaSalvar);
    }

    @Test
    void cadastrarQuestao_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        Questao questao = criarQuestaoValida(avaliacao, 1, "Enunciado Falha");
        when(questaoRepository.save(any(Questao.class))).thenThrow(new RuntimeException("Erro ao salvar no banco"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            questaoService.cadastrarQuestao(questao);
        });

        // Verify
        verify(questaoRepository, times(1)).save(questao);
    }

    // ============== listarQuestoesPorAvaliacao ==============
    @Test
    void listarQuestoesPorAvaliacao_DeveRetornarListaDeQuestoes_QuandoExistirem() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        Questao questao1 = criarQuestaoValida(avaliacao, 1, "Pergunta 1");
        Questao questao2 = criarQuestaoValida(avaliacao, 2, "Pergunta 2");
        List<Questao> listaEsperada = List.of(questao1, questao2);
        when(questaoRepository.findAllByAvaliacao(avaliacao)).thenReturn(listaEsperada);

        // Act
        List<Questao> resultado = questaoService.listarQuestoesPorAvaliacao(avaliacao);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pergunta 1", resultado.get(0).getEnunciado());
        assertEquals("Pergunta 2", resultado.get(1).getEnunciado());
        verify(questaoRepository, times(1)).findAllByAvaliacao(avaliacao);
    }

    @Test
    void listarQuestoesPorAvaliacao_DeveRetornarListaVazia_QuandoNaoExistirem() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        when(questaoRepository.findAllByAvaliacao(avaliacao)).thenReturn(Collections.emptyList());

        // Act
        List<Questao> resultado = questaoService.listarQuestoesPorAvaliacao(avaliacao);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(questaoRepository, times(1)).findAllByAvaliacao(avaliacao);
    }

    @Test
    void listarQuestoesPorAvaliacao_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        when(questaoRepository.findAllByAvaliacao(avaliacao)).thenThrow(new RuntimeException("Erro ao listar por avaliacao"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            questaoService.listarQuestoesPorAvaliacao(avaliacao);
        });

        // Verify
        verify(questaoRepository, times(1)).findAllByAvaliacao(avaliacao);
    }

    // ============== findById ==============
    @Test
    void findById_DeveRetornarQuestao_QuandoIdExistente() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);
        Questao questaoEsperada = criarQuestaoValida(avaliacao, 1, "Existe?");
        when(questaoRepository.findById(key)).thenReturn(Optional.of(questaoEsperada));

        // Act
        Optional<Questao> resultado = questaoService.findById(key);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(questaoEsperada.getEnunciado(), resultado.get().getEnunciado());
        verify(questaoRepository, times(1)).findById(key);
    }

    @Test
    void findById_DeveRetornarOptionalVazio_QuandoIdInexistente() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 999);
        when(questaoRepository.findById(key)).thenReturn(Optional.empty());

        // Act
        Optional<Questao> resultado = questaoService.findById(key);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(questaoRepository, times(1)).findById(key);
    }

    @Test
    void findById_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);
        when(questaoRepository.findById(key)).thenThrow(new RuntimeException("Erro ao buscar por ID"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            questaoService.findById(key);
        });

        // Verify
        verify(questaoRepository, times(1)).findById(key);
    }

    // ============== existsById ==============
    @Test
    void existsById_DeveRetornarTrue_QuandoQuestaoExistente() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);
        when(questaoRepository.existsById(key)).thenReturn(true);

        // Act
        Boolean resultado = questaoService.existsById(key);

        // Assert
        assertTrue(resultado);
        verify(questaoRepository, times(1)).existsById(key);
    }

    @Test
    void existsById_DeveRetornarFalse_QuandoQuestaoInexistente() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 999);
        when(questaoRepository.existsById(key)).thenReturn(false);

        // Act
        Boolean resultado = questaoService.existsById(key);

        // Assert
        assertFalse(resultado);
        verify(questaoRepository, times(1)).existsById(key);
    }

    @Test
    void existsById_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);
        when(questaoRepository.existsById(key)).thenThrow(new RuntimeException("Erro ao verificar existencia"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            questaoService.existsById(key);
        });

        // Verify
        verify(questaoRepository, times(1)).existsById(key);
    }

    // ============== deletarQuestao ==============
    @Test
    void deletarQuestao_DeveChamarDeleteByIdDoRepositorio() {
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);

        doNothing().when(questaoRepository).deleteById(key);

        questaoService.deletarQuestao(key);

        verify(questaoRepository, times(1)).deleteById(key);
    }

    @Test
    void deletarQuestao_DeveExecutarSemErro_QuandoQuestaoInexistente() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 999);
        doNothing().when(questaoRepository).deleteById(key);

        // Act & Assert
        assertDoesNotThrow(() -> {
            questaoService.deletarQuestao(key);
        });

        // Verify
        verify(questaoRepository, times(1)).deleteById(key);
    }

    @Test
    void deletarQuestao_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(1L);
        QuestaoCompoundKey key = new QuestaoCompoundKey(avaliacao.getIdAvaliacao(), 1);
        doThrow(new RuntimeException("Erro ao deletar")).when(questaoRepository).deleteById(key);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            questaoService.deletarQuestao(key);
        });

        // Verify
        verify(questaoRepository, times(1)).deleteById(key);
    }
}
