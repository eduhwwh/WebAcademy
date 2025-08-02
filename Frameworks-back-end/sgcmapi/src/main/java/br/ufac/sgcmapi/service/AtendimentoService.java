package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements ICrudService<Atendimento>{


    
    private final AtendimentoRepository repo;

    
    public AtendimentoService(AtendimentoRepository repo){
        this.repo = repo;
    }
    

    @Override
    public List<Atendimento> consultar(String termoBuscar) {
        
        return repo.findAll();

    }

    @Override
    public Atendimento consultar(Long id) {
        
        return repo.findById(id).orElse(null);

    }

    @Override
    public Atendimento salvar(Atendimento objeto) {

        return repo.save(objeto);

    }

    @Override
    public void remover(Long id) {

        var registro = this.consultar(id);
        if(registro != null){
            registro.setStatus(EStatus.CANCELADO);
            repo.save(registro);
        }


    }
    
}
