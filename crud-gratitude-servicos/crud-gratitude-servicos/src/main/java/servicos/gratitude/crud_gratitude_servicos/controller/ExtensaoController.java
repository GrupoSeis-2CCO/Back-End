package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.ExtensaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.ExtensaoService;

import java.util.List;

@RestController
@RequestMapping("/extencoes")
public class ExtensaoController {

    private final ExtensaoService extensaoService;

    public ExtensaoController(ExtensaoService extensaoService) {
        this.extensaoService = extensaoService;
    }

    @GetMapping
    public ResponseEntity<List<ExtensaoResponseDto>> listarExtensaos(){
        List<Extensao> extensaos = extensaoService.listarExtensaos();

        if (extensaos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<ExtensaoResponseDto> responses = ExtensaoMapper.toEntity(extensaos);

        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtensaoResponseDto> atualizarIconeExtensao(
            @Valid @RequestBody ExtensaoUpdateDto novoIcone,
            @PathVariable Integer id
    ){
        if (!extensaoService.extensaoExistById(id)){
            return ResponseEntity.status(404).build();
        }

        Extensao extensao = ExtensaoMapper.toEntity(novoIcone);

        Extensao iconeAtualizado = extensaoService.atualizarIcone(id, extensao);

        ExtensaoResponseDto response = ExtensaoMapper.toEntity(iconeAtualizado);

        return ResponseEntity.status(200).body(response);
    }
}
