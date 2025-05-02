package servicos.gratitude.crud_gratitude_servicos.mapper;

import servicos.gratitude.crud_gratitude_servicos.entity.Extensao;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoUpdateDto;
import servicos.gratitude.crud_gratitude_servicos.entity.dto.extensao.ExtensaoResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ExtensaoMapper {

    static public Extensao toEntity(ExtensaoUpdateDto request){
        Extensao extensao = new Extensao();

        extensao.setIcone(request.getIcone());

        return extensao;
    }

    static public ExtensaoResponseDto toEntity(Extensao extensao){
        ExtensaoResponseDto response = new ExtensaoResponseDto();

        response.setTipoExtensao(extensao.getTipo_extensao());
        response.setIcone(extensao.getIcone());

        return response;
    }

    static public List<ExtensaoResponseDto> toEntity (List<Extensao> extencoes){
        List<ExtensaoResponseDto> responses = new ArrayList<>();

        for (Extensao extensaoDaVez : extencoes) {
            ExtensaoResponseDto response = new ExtensaoResponseDto();

            response.setTipoExtensao(extensaoDaVez.getTipo_extensao());
            response.setIcone(extensaoDaVez.getIcone());

            responses.add(response);
        }

        return responses;
    }
}
