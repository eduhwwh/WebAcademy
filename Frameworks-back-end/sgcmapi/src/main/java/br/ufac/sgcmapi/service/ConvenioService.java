package br.ufac.sgcmapi.service;

import java.util.List;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

public class ConvenioService implements ICrudService<Convenio>{

    private final ConvenioRepository repo;

    public ConvenioService(ConvenioRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Convenio> consultar(String termoBuscar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

    @Override
    public Convenio consultar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

    @Override
    public Convenio salvar(Convenio objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public void remover(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
    
    public List<Convenio> consultarAtivos(){
        return repo.findByAtivo(true);
    }
}
