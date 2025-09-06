package br.ufac.sgcmapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.ufac.sgcmapi.service.AtendimentoService;
import br.ufac.sgcmapi.validator.grupos.OnCreate;
import br.ufac.sgcmapi.validator.grupos.OnUpdate;

@RestController
@RequestMapping("/atendimento")
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
    @GetMapping("/consultar")
    public ResponseEntity<List<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/consultar", params = "status")
    public ResponseEntity<List<AtendimentoDto>> consultar(
            @RequestParam(required = false) String termoBusca,
            @RequestParam List<EStatus> status) {
        var registros = servico.consultar(termoBusca, status);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping(value = "/consultar", params = "page")
    public ResponseEntity<Page<AtendimentoDto>> consultar(String termoBusca, Pageable paginacao) {
        var registros = servico.consultar(termoBusca, paginacao);
        var dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/consultar/{id}")
    public ResponseEntity<AtendimentoDto> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Long> inserir(@RequestBody @Validated(OnCreate.class) AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody @Validated(OnUpdate.class) AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        servico.salvar(objetoConvertido);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<EStatus> atualizarStatus(@PathVariable Long id) {
        var registro = servico.atualizarStatus(id);
        return ResponseEntity.ok(registro.getStatus());
    }

    @GetMapping("/horarios-ocupados-profissional")
    public ResponseEntity<List<LocalTime>> consultarHorariosOcupadosProfissional(
            Long id,
            LocalDate data) {
        var horarios = servico.consultarHorariosOcupadosProfissional(id, data);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/horarios-ocupados-paciente")
    public ResponseEntity<List<LocalTime>> consultarHorariosOcupadosPaciente(
            Long id,
            LocalDate data) {
        var horarios = servico.consultarHorariosOcupadosPaciente(id, data);
        return ResponseEntity.ok(horarios);
    }

}
