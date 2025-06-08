package servicos.gratitude.crud_gratitude_servicos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import servicos.gratitude.crud_gratitude_servicos.entity.Avaliacao;
import servicos.gratitude.crud_gratitude_servicos.repository.AvaliacaoRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    private Avaliacao criarAvaliacaoValida(Integer id, Integer acertosMinimos) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(id);
        avaliacao.setAcertosMinimos(acertosMinimos);
      
        return avaliacao;
    }

    // ============== cadastrarAvaliacao ==============
    @Test
    void cadastrarAvaliacao_DeveRetornarAvaliacaoSalva_QuandoDadosValidos() {
        // Arrange
        Avaliacao avaliacaoParaSalvar = criarAvaliacaoValida(null, 5); 
        Avaliacao avaliacaoSalva = criarAvaliacaoValida(1, 5);
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacaoSalva);

        // Act
        Avaliacao resultado = avaliacaoService.cadastrarAvaliacao(avaliacaoParaSalvar);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdAvaliacao());
        assertEquals(5, resultado.getAcertosMinimos());
        verify(avaliacaoRepository, times(1)).save(avaliacaoParaSalvar);
    }

    @Test
    void cadastrarAvaliacao_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Avaliacao avaliacao = criarAvaliacaoValida(null, 5);
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenThrow(new RuntimeException("Erro ao salvar no banco"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            avaliacaoService.cadastrarAvaliacao(avaliacao);
        });

        // Verify
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    // ============== existsById ==============
    @Test
    void existsById_DeveRetornarTrue_QuandoIdExistente() {
        // Arrange
        Integer idExistente = 1;
        when(avaliacaoRepository.existsById(idExistente)).thenReturn(true);

        // Act
        Boolean resultado = avaliacaoService.existsById(idExistente);

        // Assert
        assertTrue(resultado);
        verify(avaliacaoRepository, times(1)).existsById(idExistente);
    }

    @Test
    void existsById_DeveRetornarFalse_QuandoIdInexistente() {
        // Arrange
        Integer idInexistente = 99;
        when(avaliacaoRepository.existsById(idInexistente)).thenReturn(false);

        // Act
        Boolean resultado = avaliacaoService.existsById(idInexistente);

        // Assert
        assertFalse(resultado);
        verify(avaliacaoRepository, times(1)).existsById(idInexistente);
    }

    @Test
    void existsById_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Integer id = 1;
        when(avaliacaoRepository.existsById(id)).thenThrow(new RuntimeException("Erro ao verificar existencia"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            avaliacaoService.existsById(id);
        });

        // Verify
        verify(avaliacaoRepository, times(1)).existsById(id);
    }

    // ============== findById ==============
    @Test
    void findById_DeveRetornarAvaliacao_QuandoIdExistente() {
        // Arrange
        Integer idExistente = 1;
        Avaliacao avaliacaoEsperada = criarAvaliacaoValida(idExistente, 7);
        when(avaliacaoRepository.findById(idExistente)).thenReturn(Optional.of(avaliacaoEsperada));

        // Act
        Optional<Avaliacao> resultado = avaliacaoService.findById(idExistente);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(avaliacaoEsperada.getAcertosMinimos(), resultado.get().getAcertosMinimos());
        verify(avaliacaoRepository, times(1)).findById(idExistente);
    }

    @Test
    void findById_DeveRetornarOptionalVazio_QuandoIdInexistente() {
        // Arrange
        Integer idInexistente = 99;
        when(avaliacaoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Optional<Avaliacao> resultado = avaliacaoService.findById(idInexistente);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(avaliacaoRepository, times(1)).findById(idInexistente);
    }

    @Test
    void findById_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Integer id = 1;
        when(avaliacaoRepository.findById(id)).thenThrow(new RuntimeException("Erro ao buscar por ID"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            avaliacaoService.findById(id);
        });

        // Verify
        verify(avaliacaoRepository, times(1)).findById(id);
    }

    // ============== atualizarAcertosMinimos ==============
    @Test
    void atualizarAcertosMinimos_DeveRetornarAvaliacaoAtualizada_QuandoDadosValidos() {
        // Arrange
        Integer idAvaliacao = 1;
       
        Avaliacao avaliacaoPayload = criarAvaliacaoValida(null, 10); 

        Avaliacao avaliacaoSalvaEsperada = criarAvaliacaoValida(idAvaliacao, 10);

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacaoSalvaEsperada);

        // Act

        Avaliacao resultado = avaliacaoService.atualizarAcertosMinimos(avaliacaoPayload, idAvaliacao);

        // Assert
        assertNotNull(resultado);
        assertEquals(idAvaliacao, resultado.getIdAvaliacao());
        assertEquals(10, resultado.getAcertosMinimos());

        ArgumentCaptor<Avaliacao> captor = ArgumentCaptor.forClass(Avaliacao.class);
        verify(avaliacaoRepository, times(1)).save(captor.capture());
        Avaliacao argumentoSavado = captor.getValue();

        assertEquals(idAvaliacao, argumentoSavado.getIdAvaliacao()); 
        assertEquals(10, argumentoSavado.getAcertosMinimos());
    }

    @Test
    void atualizarAcertosMinimos_DeveLancarExcecao_QuandoRepositorioFalhar() {
        // Arrange
        Integer idAvaliacao = 1;
        Avaliacao avaliacaoPayload = criarAvaliacaoValida(null, 6);

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenThrow(new RuntimeException("Erro ao atualizar no banco"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            avaliacaoService.atualizarAcertosMinimos(avaliacaoPayload, idAvaliacao);
        });


        ArgumentCaptor<Avaliacao> captor = ArgumentCaptor.forClass(Avaliacao.class);
        verify(avaliacaoRepository, times(1)).save(captor.capture());
        Avaliacao argumentoSavado = captor.getValue();

        assertEquals(idAvaliacao, argumentoSavado.getIdAvaliacao());
        assertEquals(6, argumentoSavado.getAcertosMinimos());
    }
}