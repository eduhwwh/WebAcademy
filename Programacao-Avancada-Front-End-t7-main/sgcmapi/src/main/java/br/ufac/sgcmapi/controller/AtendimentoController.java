package br.ufac.sgcmapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
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

import br.ufac.sgcmapi.controller.dto.AtendimentoDto;
import br.ufac.sgcmapi.controller.mapper.AtendimentoMapper;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.model.RespostaErro;
import br.ufac.sgcmapi.service.AtendimentoService;
import br.ufac.sgcmapi.validator.grupos.OnCreate;
import br.ufac.sgcmapi.validator.grupos.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/atendimento")
@Tag(name = "Atendimento", description = "Endpoints para gerenciar atendimentos")
public class AtendimentoController implements ICrudController<AtendimentoDto>, IPageController<AtendimentoDto> {

    private final AtendimentoService servico;
    private final AtendimentoMapper mapper;

    public AtendimentoController(
            AtendimentoService servico,
            AtendimentoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping(value = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/consultar", params = "status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @RequestParam(required = false) List<EStatus> status) {
        var registros = servico.consultar(termoBusca, status);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @SortDefaults({
                @SortDefault(sort = "data", direction = Sort.Direction.DESC),
                @SortDefault(sort = "hora", direction = Sort.Direction.ASC)
            })
            @ParameterObject Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        var dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/consultar", params = {"status", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obter todos os atendimentos ou filtrar por termo de busca e status (com opção de paginação)",
        description = "Obtém uma lista de todos os atendimentos cadastrados no sistema ou que contenham o termo de busca ou o status informado.")
    public ResponseEntity<Page<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @RequestParam List<EStatus> status,
            @SortDefaults({
                @SortDefault(sort = "data", direction = Sort.Direction.DESC),
                @SortDefault(sort = "hora", direction = Sort.Direction.ASC)
            })
           Pageable paginacao) {
        var registros = servico.consultar(termoBusca, status, paginacao);
        var dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/consultar/{id}")
    @Operation(
        summary = "Obter um atendimento",
        description = "Obtém um atendimento cadastrado no sistema baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atendimento encontrado"),
        @ApiResponse(responseCode = "404", description = "Atendimento não encontrado", content = @Content())
    })
    public ResponseEntity<AtendimentoDto> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Cadastrar um novo atendimento",
        description = "Cadastra um novo atendimento no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Atendimento cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Long> inserir(@RequestBody @Validated(OnCreate.class) AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Atualizar um atendimento",
        description = "Atualiza um atendimento cadastrado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Atendimento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = RespostaErro.class)))
    })
    public ResponseEntity<Void> atualizar(@RequestBody @Validated(OnUpdate.class) AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        servico.salvar(objetoConvertido);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/remover/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
        summary = "Cancelar um agendamento",
        description = "Altera o status de um agendamento para CANCELADO.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Atualizar o status de um atendimento",
        description = "Altera o status para o próximo valor de acordo com o fluxo de atendimento: AGENDADO > CONFIRMADO > CHEGADA > ATENDIMENTO > ENCERRADO.")
    public ResponseEntity<EStatus> atualizarStatus(@PathVariable Long id) {
        var registro = servico.atualizarStatus(id);
        return ResponseEntity.ok(registro.getStatus());
    }

    @GetMapping(value = "/horarios-ocupados-profissional", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obter horários ocupados para um profissional",
        description = "Obtém uma lista de horários ocupados para um profissional em uma determinada data.")
    public ResponseEntity<List<LocalTime>> consultarHorariosOcupadosProfissional(
            Long id,
            LocalDate data) {
        var horarios = servico.consultarHorariosOcupadosProfissional(id, data);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping(value = "/horarios-ocupados-paciente", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Obter horários ocupados para um paciente",
        description = "Obtém uma lista de horários ocupados para um paciente em uma determinada data.")
    public ResponseEntity<List<LocalTime>> consultarHorariosOcupadosPaciente(
            Long id,
            LocalDate data) {
        var horarios = servico.consultarHorariosOcupadosPaciente(id, data);
        return ResponseEntity.ok(horarios);
    }

}
