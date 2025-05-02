package servicos.gratitude.crud_gratitude_servicos.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.material.MaterialUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.CursoMapper;
import servicos.gratitude.crud_gratitude_servicos.mapper.MaterialMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.ExtensaoService;
import servicos.gratitude.crud_gratitude_servicos.service.MaterialService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiais ")
public class MaterialController {

    private final ExtensaoService extensaoService;
    private final MaterialService materialService;
    private final CursoService cursoService;

    public MaterialController(ExtensaoService extensaoService, MaterialService materialService, CursoService cursoService) {
        this.extensaoService = extensaoService;
        this.materialService = materialService;
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<MaterialResponseDto> cadastrarMaterial(
            @Valid @RequestBody MaterialRequestDto request
    ){
        Optional<Extensao> extensao = extensaoService.findById(request.getFk_extensao());
        Optional<Curso> curso = cursoService.findById(request.getFk_curso());

        if (extensao.isEmpty() || curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Material material = MaterialMapper.toEntity(request, curso.get(), extensao.get());
        Material materialCadastrado = materialService.cadastrarMaterial(material);
        MaterialResponseDto response = MaterialMapper.toEntity(materialCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponseDto>> listarMateriais(){
        List<Material> materiais = materialService.listarMaterial();

        if (materiais.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MaterialResponseDto> responses = MaterialMapper.toEntity(materiais);

        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("listar-por-curso")
    public ResponseEntity<List<MaterialResponseDto>> listarMateriais(
            @RequestParam Integer idCurso
    ){
        Optional<Curso> curso = cursoService.findById(idCurso);

        if (curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Material> materiais = materialService.listarMaterialPorCurso(curso.get());

        if (materiais.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MaterialResponseDto> responses = MaterialMapper.toEntity(materiais);

        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/atualizar-dados/{id}")
    public ResponseEntity<MaterialResponseDto> atualizarDadosMaterial(
            @Valid @RequestBody MaterialUpdateDto update,
            @PathVariable Integer idMaterial
    ){
        Optional<Material> material = materialService.findById(idMaterial);

        if (material.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Material materialParaAtualizar = MaterialMapper.toEntity(update, material.get());
        Material materialAtualizado = materialService.atualizarmaterial(materialParaAtualizar, idMaterial);
        MaterialResponseDto response = MaterialMapper.toEntity(materialAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/atualizar-oculto/{id}")
    public ResponseEntity<MaterialResponseDto> atualizarOcultoMaterial(
            @PathVariable Integer idMaterial
    ){
        if (!materialService.existsById(idMaterial)){
            return ResponseEntity.status(404).build();
        }

        Boolean materialIsOculto = materialService.isOculto(idMaterial);
        Material ocultoAtualizado = materialService.atualizarOculto(idMaterial, !materialIsOculto);
        MaterialResponseDto response = MaterialMapper.toEntity(ocultoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMaterial(
            @PathVariable Integer idMaterial
    ){
        if (!materialService.existsById(idMaterial)){
            return ResponseEntity.status(404).build();
        }

        materialService.deletarMaterial(idMaterial);

        return ResponseEntity.status(200).build();
    }
}
