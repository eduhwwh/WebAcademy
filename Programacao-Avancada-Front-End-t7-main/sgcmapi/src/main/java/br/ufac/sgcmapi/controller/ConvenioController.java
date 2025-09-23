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

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.model.RespostaErro;
import br.ufac.sgcmapi.service.ConvenioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/convenio")
@Tag(name = "Convenio", description = "Endpoints para gerenciar convênios")
public class ConvenioController implements ICrudController<Convenio>, IPageController<Convenio> {

    private final ConvenioService servico;

    public ConvenioController(ConvenioService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<Convenio>> consultar(@RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page")
    @Operation(
        summary = "Obter todos os convênios ou filtrar por termo de busca (com opção de paginação)",
        description = "Obtém uma lista de todos os convênios cadastrados no sistema ou que contenham o termo de busca informado.")
    public ResponseEntity<Page<Convenio>> consultar(
            @RequestParam(required = false) String termoBusca,
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            @ParameterObject Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/consultar/{id}")
    @Operation(
        summary = "Obter um convênio",
        description = "Obtém um convênio cadastrado no sistema baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Convênio encontrado"),
        @ApiResponse(responseCode = "404", description = "Convênio não encontrado", content = @Content())
    })
    public ResponseEntity<Convenio> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Cadastrar um novo convênio",
        description = "Cadastra um novo convênio no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Convênio cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Long> inserir(@RequestBody Convenio objeto) {
        var registro = servico.salvar(objeto);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Atualizar um convênio",
        description = "Atualiza um convênio cadastrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Convênio atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Void> atualizar(@RequestBody Convenio objeto) {
        servico.salvar(objeto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Remover um convênio",
        description = "Remove um convênio cadastrado no sistema.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/ativos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obter convênios ativos",
        description = "Obtém uma lista de todos os convênios ativos cadastrados no sistema.")
    public ResponseEntity<List<Convenio>> consultarAtivos() {
        var registros = servico.consultarAtivos();
        return ResponseEntity.ok(registros);
    }
    
}
