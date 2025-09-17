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

import br.ufac.sgcmapi.controller.dto.ProfissionalDto;
import br.ufac.sgcmapi.controller.mapper.ProfissionalMapper;
import br.ufac.sgcmapi.model.RespostaErro;
import br.ufac.sgcmapi.service.ProfissionalService;
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
@RequestMapping("/profissional")
@Tag(name = "Profissional", description = "Endpoints para gerenciar profissionais")
public class ProfissionalController implements ICrudController<ProfissionalDto>, IPageController<ProfissionalDto> {

    private final ProfissionalService servico;
    private final ProfissionalMapper mapper;

    public ProfissionalController(
            ProfissionalService servico,
            ProfissionalMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<ProfissionalDto>> consultar(@RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page")
    @Operation(
        summary = "Obter todos os profissionais ou filtrar por termo de busca (com opção de paginação)",
        description = "Obtém uma lista de todos os profissionais cadastrados no sistema ou que contenham o termo de busca informado.")
    public ResponseEntity<Page<ProfissionalDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            @ParameterObject Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        var dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/consultar/{id}")
    @Operation(
        summary = "Obter um profissional",
        description = "Obtém um profissional cadastrado no sistema baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profissional encontrado"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado", content = @Content())
    })
    public ResponseEntity<ProfissionalDto> consultar(@PathVariable Long id) {
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
        summary = "Cadastrar um novo profissional",
        description = "Cadastra um novo profissional no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profissional cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Long> inserir(@RequestBody @Validated(OnCreate.class) ProfissionalDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping(value = "/atualizar", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Atualizar um profissional",
        description = "Atualiza um profissional cadastrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profissional atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Void> atualizar(@RequestBody @Validated(OnUpdate.class) ProfissionalDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        servico.salvar(objetoConvertido);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Remover um profissional",
        description = "Remove um profissional cadastrado no sistema.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }
    
}
