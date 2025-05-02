package servicos.gratitude.crud_gratitude_servicos.service;

import org.apache.el.parser.BooleanNode;
import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Curso;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {


    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {this.materialRepository = materialRepository;}

    public Material cadastrarMaterial(Material material){
        return materialRepository.save(material);
    }

    public List<Material> listarMaterial(){
        return materialRepository.findAll();
    }

    public List<Material> listarMaterialPorCurso(Curso fkCurso){
        return materialRepository.findByFkCurso(fkCurso);
    }

    public Optional<Material> findById(Integer id){
        return materialRepository.findById(id);
    }

    public Material atualizarmaterial(Material material, Integer id){
        material.setId_material(id);
        return materialRepository.save(material);
    }

    public Boolean existsById(Integer id){
        return materialRepository.existsById(id);
    }

    public void deletarMaterial(Integer id){
        materialRepository.deleteById(id);
    }

    public Boolean isOculto(Integer id){
        return materialRepository.findById(id).get().getOculto();
    }

    public Material atualizarOculto(Integer id, Boolean novoOculto){
        materialRepository.findById(id).get().setOculto(novoOculto);
        return materialRepository.findById(id).get();
    }
}
