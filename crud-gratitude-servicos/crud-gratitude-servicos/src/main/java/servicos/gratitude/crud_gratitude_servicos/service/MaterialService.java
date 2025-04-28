package servicos.gratitude.crud_gratitude_servicos.service;

import org.springframework.stereotype.Service;
import servicos.gratitude.crud_gratitude_servicos.entity.Feedback;
import servicos.gratitude.crud_gratitude_servicos.entity.Material;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;
import servicos.gratitude.crud_gratitude_servicos.repository.MaterialRepository;

import java.util.List;

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

    public void deletarMaterial(Integer id){
        materialRepository.deleteById(id);
    }

    public Material atualizarmaterial(Material material){
        return materialRepository.save(material);
    }

}
