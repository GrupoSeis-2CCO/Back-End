package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.ExtensaoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.ExtensaoService;

import java.util.List;

@Tag(name = "Extensões", description = "Gerencia todas as operações relacionadas às extensões")
@RequiredArgsConstructor
@RestController
@RequestMapping("/extensoes")
public class ExtensaoController {
    private final ExtensaoService extensaoService;

    @GetMapping
    @Operation(summary = "Listar Extensões", description = "Retorna a lista de todas as extensões disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de extensões retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExtensaoResponseDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma extensão encontrada", content = @Content)
    })
    public ResponseEntity<List<ExtensaoResponseDto>> listarExtensaos() {
        List<Extensao> extensaos = extensaoService.listarExtensaos();

        if (extensaos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ExtensaoResponseDto> responses = ExtensaoMapper.toEntity(extensaos);

        return ResponseEntity.status(200).body(responses);
    }
}