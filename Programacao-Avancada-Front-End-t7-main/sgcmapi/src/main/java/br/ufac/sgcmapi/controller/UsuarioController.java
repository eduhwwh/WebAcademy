package br.ufac.sgcmapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.controller.dto.UsuarioDto;
import br.ufac.sgcmapi.controller.mapper.UsuarioMapper;
import br.ufac.sgcmapi.model.RespostaErro;
import br.ufac.sgcmapi.service.UsuarioService;
import br.ufac.sgcmapi.validator.grupos.OnCreate;
import br.ufac.sgcmapi.validator.grupos.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/config/usuario")
@Tag(name = "Usuario", description = "Endpoints para gerenciar usuários")
public class UsuarioController implements ICrudController<UsuarioDto>, IPageController<UsuarioDto> {

    private final UsuarioService servico;
    private final UsuarioMapper mapper;

    public UsuarioController(
            UsuarioService servico,
            UsuarioMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<UsuarioDto>> consultar(@RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page")
    @Operation(
        summary = "Obter todos os usuários ou filtrar por termo de busca (com opção de paginação)",
        description = "Obtém uma lista de todos os usuários cadastrados no sistema ou que contenham o termo de busca informado.")
    public ResponseEntity<Page<UsuarioDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @SortDefault(sort = "nomeCompleto", direction = Sort.Direction.ASC)
            @ParameterObject Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        var dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/consultar/{id}")
    @Operation(
        summary = "Obter um usuário",
        description = "Obtém um usuário cadastrado no sistema baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content())
    })
    public ResponseEntity<UsuarioDto> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping(value = "/inserir", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Cadastrar um novo usuário",
        description = "Cadastra um novo usuário no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Long> inserir(@RequestBody @Validated(OnCreate.class) UsuarioDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping(value = "/atualizar", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Atualizar um usuário",
        description = "Atualiza um usuário cadastrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Void> atualizar(@RequestBody @Validated(OnUpdate.class) UsuarioDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        servico.salvar(objetoConvertido);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Remover um usuário",
        description = "Remove um usuário cadastrado no sistema.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }
    
}
