package servicos.gratitude.crud_gratitude_servicos.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.gratitude.crud_gratitude_servicos.entity.Apostila;
import servicos.gratitude.crud_gratitude_servicos.entity.MaterialAluno;
import servicos.gratitude.crud_gratitude_servicos.entity.Matricula;
import servicos.gratitude.crud_gratitude_servicos.entity.Video;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MaterialAlunoCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.compoundKeys.MatriculaCompoundKey;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno.MaterialAlunoRequestDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno.MaterialAlunoResponseDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.materialAluno.MaterialAlunoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.mapper.MaterialAlunoMapper;
import servicos.gratitude.crud_gratitude_servicos.mapper.MatriculaMapper;
import servicos.gratitude.crud_gratitude_servicos.service.ApostilaService;
import servicos.gratitude.crud_gratitude_servicos.service.MaterialAlunoService;
import servicos.gratitude.crud_gratitude_servicos.service.MatriculaService;
import servicos.gratitude.crud_gratitude_servicos.service.VideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/materiais-alunos")
public class MaterialAlunoController {
    private final MaterialAlunoService materialAlunoService;
    private final VideoService videoService;
    private final ApostilaService apostilaService;
    private final MatriculaService matriculaService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<MaterialAlunoResponseDto> cadastrarMaterialAluno(
            @Valid @RequestBody MaterialAlunoRequestDto request
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(request.getFkUsuario(), request.getFkCurso());
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Optional<Video> video = videoService.findById(request.getFkVideo());
        Optional<Apostila> apostila = apostilaService.findById(request.getFkApostila());

        if (video.isEmpty() && apostila.isEmpty()){
            return ResponseEntity.status(404).build();
        } else if (video.isPresent() && apostila.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        List<MaterialAluno> materiaisAluno = materialAlunoService.listarMaterialAlunoPorMatricula(matricula.get());
        Integer maiorId = 0;
        for (MaterialAluno materialAlunoDaVez : materiaisAluno) {
            if(materialAlunoDaVez.getIdMaterialAlunoComposto().getIdMaterialAluno() > maiorId){
                maiorId = materialAlunoDaVez.getIdMaterialAlunoComposto().getIdMaterialAluno();
            }
        }
        Integer idMaterialAluno = maiorId + 1;

        MaterialAlunoCompoundKey idMaterialAlunoComposto = MaterialAlunoMapper.toEntity(idMaterialAluno, idMatriculaComposto);

        if (materialAlunoService.existsById(idMaterialAlunoComposto)){
            return ResponseEntity.status(409).build();
        }

        MaterialAluno materialAluno = MaterialAlunoMapper.toEntity(request, idMaterialAlunoComposto, matricula.get(), video.orElse(null), apostila.orElse(null));
        MaterialAluno materialAlunoCadastrado = materialAlunoService.cadastrarMaterialAluno(materialAluno);
        MaterialAlunoResponseDto response = MaterialAlunoMapper.toEntity(materialAlunoCadastrado);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("listar-por-matricula/{fkUsuario}/{fkCurso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<MaterialAlunoResponseDto>> listarMateriaisPorMatricula(
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<MaterialAluno> materiaisAluno = materialAlunoService.listarMaterialAlunoPorMatricula(matricula.get());

        if (materiaisAluno.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MaterialAlunoResponseDto> responses = MaterialAlunoMapper.toEntity(materiaisAluno);
        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("listar-por-matricula-e-finalizacao/{fkUsuario}/{fkCurso}/{isFinalizado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<MaterialAlunoResponseDto>> listarMateriaisPorMatricula(
            @PathVariable Boolean isFinalizado,
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        List<MaterialAluno> materiaisAluno = materialAlunoService.listarMaterialAlunoPorMatriculaEFinalizacao(matricula.get(), isFinalizado);

        if (materiaisAluno.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<MaterialAlunoResponseDto> responses = MaterialAlunoMapper.toEntity(materiaisAluno);
        return ResponseEntity.status(200).body(responses);
    }

    @PutMapping("atualizar-finalizado/{idMaterialAluno}/{idUsuario}/{idCurso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<MaterialAlunoResponseDto> atualizarFinalizado(
            @PathVariable Integer idMaterialAluno,
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MaterialAlunoCompoundKey idMaterialAlunoComposto = MaterialAlunoMapper.toEntity(idMaterialAluno, idMatriculaComposto);
        Optional<MaterialAluno> materialAluno = materialAlunoService.findById(idMaterialAlunoComposto);

        if (materialAluno.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MaterialAluno materialAlunoAtualizado = materialAlunoService.atualizarIsFinalizado(materialAluno.get());
        MaterialAlunoResponseDto response = MaterialAlunoMapper.toEntity(materialAlunoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("atualizar-ultimo-acesso/{idMaterialAuno}/{idUsuario}/{idCurso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<MaterialAlunoResponseDto> atualizarUltimoAcesso(
            @Valid @RequestBody MaterialAlunoUpdateDto update,
            @PathVariable Integer idMaterialAluno,
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MaterialAlunoCompoundKey idMaterialAlunoComposto = MaterialAlunoMapper.toEntity(idMaterialAluno, idMatriculaComposto);
        Optional<MaterialAluno> materialAluno = materialAlunoService.findById(idMaterialAlunoComposto);

        if (materialAluno.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MaterialAluno materialAlunoAtualizado = materialAlunoService.atualizarUltimoAcesso(materialAluno.get(), update.getUltimoAcessoNovo());
        MaterialAlunoResponseDto response = MaterialAlunoMapper.toEntity(materialAlunoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{idMaterialAluno}/{idUsuario}/{idCurso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity deletarMaterialAluno(
            @PathVariable Integer idMaterialAluno,
            @PathVariable Integer fkUsuario,
            @PathVariable Integer fkCurso
    ){
        MatriculaCompoundKey idMatriculaComposto = MatriculaMapper.toEntity(fkUsuario, fkCurso);
        Optional<Matricula> matricula = matriculaService.findById(idMatriculaComposto);

        if (matricula.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        MaterialAlunoCompoundKey idMaterialAlunoComposto = MaterialAlunoMapper.toEntity(idMaterialAluno, idMatriculaComposto);
        if (!materialAlunoService.existsById(idMaterialAlunoComposto)){
            return ResponseEntity.status(404).build();
        }

        materialAlunoService.deletarMaterialAluno(idMaterialAlunoComposto);
        return ResponseEntity.status(200).build();
    }
}
