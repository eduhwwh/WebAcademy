package br.ufac.sgcmapi.controller;

import java.util.List;

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

import br.ufac.sgcmapi.controller.dto.UsuarioDto;
import br.ufac.sgcmapi.controller.mapper.UsuarioMapper;
import br.ufac.sgcmapi.service.UsuarioService;


@RestController
@RequestMapping("/config/usuario")
public class UsuarioController implements ICrudController<UsuarioDto> {

    private final UsuarioService servico;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService servico, UsuarioMapper mapper) {
        
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<UsuarioDto>> consultar(@RequestParam(required = false) String termoBusca) {
        var registros = servico.consultar(termoBusca);
        var dtos = registros.stream().map( mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/consultar/{id}")
    public ResponseEntity<UsuarioDto> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Long> inserir(@RequestBody UsuarioDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.salvar(objetoConvertido);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody UsuarioDto objeto) {
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
    
}
