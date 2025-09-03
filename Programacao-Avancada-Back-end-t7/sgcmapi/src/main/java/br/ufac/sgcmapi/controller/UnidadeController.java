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

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

@RestController
@RequestMapping("/config/unidade")
public class UnidadeController implements ICrudController<Unidade> {

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
    @GetMapping("/consultar/{id}")
    public ResponseEntity<Unidade> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Long> inserir(@RequestBody Unidade objeto) {
        var registro = servico.salvar(objeto);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody Unidade objeto) {
        servico.salvar(objeto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servico.remover(id);
        return ResponseEntity.ok().build();
    }
    
}
