package br.ufac.sgcmapi.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ufac.sgcmapi.controller.dto.UsuarioDto;
import br.ufac.sgcmapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "senha", ignore = true)
    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioDto dto);
    
}
