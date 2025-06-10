package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Alternativa;
import servicos.gratitude.crud_gratitude_servicos.entity.RespostaDoUsuario;
import servicos.gratitude.crud_gratitude_servicos.entity.Tentativa;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.*;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario.RespostaDoUsuarioRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.respostaDoUsuario.RespostaDoUsuarioResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.*;
import servicos.gratitude.crud_gratitude_servicos.service.AlternativaService;
import servicos.gratitude.crud_gratitude_servicos.service.RespostaDoUsuarioService;
import servicos.gratitude.crud_gratitude_servicos.service.TentativaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/respostas-do-usuario")
public class RespostaDoUsuarioController {
    private final RespostaDoUsuarioService respostaDoUsuarioService;
    private final TentativaService tentativaService;
    private final AlternativaService alternativaService;

    @PostMapping
    public ResponseEntity<RespostaDoUsuarioResponseDto> cadastrarRespostaDoUsuario(
            @Valid RespostaDoUsuarioRequestDto request
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(request.getFkUsuario(), request.getFkCurso());
        TentativaCompoundKey idTentativaComposto = TentativaMapper.toEntity(request.getFkTentativa(), idMatriculaComposto);
        Optional<Tentativa> tentativa = tentativaService.findById(idTentativaComposto);

        if (tentativa.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        QuestaoCompoundKey idQuestaoComposto = QuestaoMapper.toEntity(request.getFkQuestao(), request.getFkAvaliacao());
        AlternativaCompoundKey idAlternativaComposto = AlternativaMapper.toEntity(request.getFkAlternativa(), idQuestaoComposto);
        Optional<Alternativa> alternativa = alternativaService.findById(idAlternativaComposto);

        if (alternativa.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        RespostaDoUsuarioCompoundKey idRespostaDoUsuarioComposto = RespostaDoUsuarioMapper.toEntity(idTentativaComposto, idAlternativaComposto);

        if (respostaDoUsuarioService.existsById(idRespostaDoUsuarioComposto)){
            return ResponseEntity.status(409).build();
        }

        RespostaDoUsuario respostaDoUsuario = RespostaDoUsuarioMapper.toEntity(idRespostaDoUsuarioComposto, tentativa.get(), alternativa.get());
        RespostaDoUsuario respostaDoUsuarioCadastrada = respostaDoUsuarioService.cadastrarRespostaDoUsuario(respostaDoUsuario);
        RespostaDoUsuarioResponseDto response = RespostaDoUsuarioMapper.toEntity(respostaDoUsuarioCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/listar-respostas-do-usuario/{fkUsuario}")
    public ResponseEntity<List<RespostaDoUsuarioResponseDto>> listarRespostasDoUsuario(
            @PathVariable Integer fkUsuario
    ){
        List<RespostaDoUsuario> respostasDosUsuarios = respostaDoUsuarioService.listarRespostasDosUsuarios();
        List<RespostaDoUsuario> respostasDoUsuario = new ArrayList<>();
        for (RespostaDoUsuario resposta : respostasDosUsuarios) {
            RespostaDoUsuarioCompoundKey chave = resposta.getRespostaDoUsuarioCompoundKey();


            if (chave.getFkUsuario().equals(fkUsuario)) {
                respostasDoUsuario.add(resposta);
            }
        }

        if (respostasDoUsuario.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<RespostaDoUsuarioResponseDto> responses = RespostaDoUsuarioMapper.toEntity(respostasDoUsuario);
        return ResponseEntity.status(200).body(responses);
    }
}
