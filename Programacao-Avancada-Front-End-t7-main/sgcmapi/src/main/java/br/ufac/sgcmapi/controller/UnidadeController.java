package br.ufac.sgcmapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.model.RespostaErro;
import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/config/unidade")
@Tag(name = "Unidade", description = "Endpoints para gerenciar unidades")
public class UnidadeController implements ICrudController<Unidade>, IPageController<Unidade> {

    private UnidadeService servico;

    public UnidadeController(UnidadeService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<Unidade>> consultar(@RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page")
    @Operation(
        summary = "Obter todas as unidades ou filtrar por termo de busca (com opção de paginação)",
        description = "Obtém uma lista de todas as unidades cadastradas no sistema ou que contenham o termo de busca informado.")
    public ResponseEntity<Page<Unidade>> consultar(
            @RequestParam(required = false) String termoBusca,
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            @ParameterObject Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/consultar/{id}")
    @Operation(
        summary = "Obter uma unidade",
        description = "Obtém uma unidade cadastrada no sistema baseada no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Unidade encontrada"),
        @ApiResponse(responseCode = "404", description = "Unidade não encontrada", content = @Content())
    })
    public ResponseEntity<Unidade> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Cadastrar uma nova unidade",
        description = "Cadastra uma nova unidade no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Unidade cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Long> inserir(@RequestBody Unidade objeto) {
        var registro = servico.salvar(objeto);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Atualizar uma unidade",
        description = "Atualiza uma unidade cadastrada no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Unidade atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Void> atualizar(@RequestBody Unidade objeto) {
        servico.salvar(objeto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Remover uma unidade",
        description = "Remove uma unidade cadastrada no sistema.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }
    
}
