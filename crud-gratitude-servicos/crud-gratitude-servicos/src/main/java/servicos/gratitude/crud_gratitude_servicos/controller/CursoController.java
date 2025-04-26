package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.curso.CursoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.mapper.CursoMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<CursoResponseDto> cadastrarCurso(
            @Valid @RequestBody CursoRequestDto request
    ){
        Curso curso = CursoMapper.toEntity(request);
        Curso cursoCadastrado = cursoService.cadastrarCurso(curso);
        CursoResponseDto response = CursoMapper.toEntity(cursoCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDto>> listarCursos(){
        List<Curso> cursos = cursoService.listarCursos();

        if (cursos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<CursoResponseDto> responses = CursoMapper.toEntity(cursos);

        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CursoResponseDto> atualizarCurso(
            @Valid @RequestBody CursoRequestDto request,
            @PathVariable Integer id
    ){
        if (!cursoService.cursoExistsById(id)){
            return ResponseEntity.status(404).build();
        }

        Curso curso = CursoMapper.toEntity(request);
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        CursoResponseDto response = CursoMapper.toEntity(cursoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDto> atualizarOculto(
            @PathVariable Integer id
    ){
        if (!cursoService.cursoExistsById(id)){
            return ResponseEntity.status(404).build();
        }

        Boolean cursoIsOculto = cursoService.cursoIsOculto(id);
        Curso ocultoAtualizado = cursoService.atualizarOculto(id, !cursoIsOculto);
        CursoResponseDto response = CursoMapper.toEntity(ocultoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCurso(
        @PathVariable Integer id
    ){
        if (!cursoService.cursoExistsById(id)){
            return ResponseEntity.status(404).build();
        }

        cursoService.deletarCurso(id);

        return ResponseEntity.status(200).build();
    }
}