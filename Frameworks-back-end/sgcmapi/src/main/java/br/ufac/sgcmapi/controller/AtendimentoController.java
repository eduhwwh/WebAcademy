package br.ufac.sgcmapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController implements ICrudController<Atendimento> {

    private final AtendimentoService servico;

    public AtendimentoController(AtendimentoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<Atendimento>> consultar(@RequestParam(required = false) String termoBusca) {
        var registro = servico.consultar(termoBusca);
        return ResponseEntity.ok(registro);
    }

    @Override
    @GetMapping("/consultar/{id}")
    public ResponseEntity<Atendimento> consultar(@PathVariable Long id) {
        var registro = servico.consultar(id);

        // resolução da atividade do dia 01/08/2025
        if(registro == null){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Long> inserir(@RequestBody Atendimento objeto) {
        var registro = servico.salvar(objeto);
        return ResponseEntity.created(null).body(registro.getId());
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody Atendimento objeto) {
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
