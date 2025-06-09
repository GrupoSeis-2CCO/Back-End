package servicos.gratitude.crud_gratitude_servicos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula.MatriculaRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.matricula.MatriculaResponseDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.MatriculaMapper;
import servicos.gratitude.crud_gratitude_servicos.service.CursoService;
import servicos.gratitude.crud_gratitude_servicos.service.MatriculaService;
import servicos.gratitude.crud_gratitude_servicos.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final UsuarioService usuarioService;
    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<MatriculaResponseDto> cadastrarMatricula(
            @Valid @RequestBody MatriculaRequestDto request
    ){
        Optional<Usuario> usuario = usuarioService.findById(request.getFkUsuario());
        Optional<Curso> curso = cursoService.findById(request.getFkCurso());

        if(usuario.isEmpty() || curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(usuario.get().getIdUsuario(), curso.get().getIdCurso());

        if (matriculaService.existsById(idMatriculaComposto)){
            return ResponseEntity.status(409).build();
        }

        Matricula matricula = MatriculaMapper.toEntity(request, idMatriculaComposto, usuario.get(), curso.get());
        Matricula matriculaCadastrada = matriculaService.cadastrarMatricula(matricula);
        MatriculaResponseDto response = MatriculaMapper.toEntity(matriculaCadastrada);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDto>> listarMatriculas(){
        List<Matricula> matriculas = matriculaService.listarMatriculas();

        if (matriculas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MatriculaResponseDto> responses = MatriculaMapper.toEntity(matriculas);
        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/listar-por-usuario/{fkUsuario}")
    public ResponseEntity<List<MatriculaResponseDto>> listarMatriculasPorUsuario(
            @PathVariable Integer fkUsuario
    ){
        Optional<Usuario> usuario = usuarioService.findById(fkUsuario);

        if (usuario.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Matricula> matriculas = matriculaService.listarMatriculasPorUsuario(usuario.get());

        if (matriculas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MatriculaResponseDto> responses = MatriculaMapper.toEntity(matriculas);
        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/listar-por-curso/{fkCurso}")
    public ResponseEntity<List<MatriculaResponseDto>> listarMatriculasPorCurso(
            @PathVariable Integer fkCurso
    ){
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if (curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<Matricula> matriculas = matriculaService.listarMatriculasPorCurso(curso.get());

        if (matriculas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MatriculaResponseDto> responses = MatriculaMapper.toEntity(matriculas);
        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/listar-por-completude/{isCompleto}")
    public ResponseEntity<List<MatriculaResponseDto>> listarMatriculasPorUsuario(
            @PathVariable Boolean isCompleto
    ){
        List<Matricula> matriculas = matriculaService.listarMatriculasPorCompletude(isCompleto);

        if (matriculas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MatriculaResponseDto> responses = MatriculaMapper.toEntity(matriculas);
        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("atualizar-ultimo-acesso/{fkUsuario}/{fkCurso}")
    public ResponseEntity<MatriculaResponseDto> atualizarUltimoAcesso(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        Optional<Usuario> usuario = usuarioService.findById(fkUsuario);
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if(usuario.isEmpty() || curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);

        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Matricula matriculaAtualizada = matriculaService.atualizarUltimoAcesso(matricula.get());
        MatriculaResponseDto response = MatriculaMapper.toEntity(matriculaAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("completar-matricula/{fkUsuario}/{fkCurso}")
    public ResponseEntity<MatriculaResponseDto> completarMatricula(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        Optional<Usuario> usuario = usuarioService.findById(fkUsuario);
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if(usuario.isEmpty() || curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);

        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Matricula matriculaAtualizada = matriculaService.completarMatricula(matricula.get());
        MatriculaResponseDto response = MatriculaMapper.toEntity(matriculaAtualizada);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{fkUsuario}/{fkCurso}")
    public ResponseEntity deletarMatricula(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        Optional<Usuario> usuario = usuarioService.findById(fkUsuario);
        Optional<Curso> curso = cursoService.findById(fkCurso);

        if(usuario.isEmpty() || curso.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);

        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        matriculaService.deletarMatricula(idMatriculaComposto);
        return ResponseEntity.status(200).build();
    }

}
