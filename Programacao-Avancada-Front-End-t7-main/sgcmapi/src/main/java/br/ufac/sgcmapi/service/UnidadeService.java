package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.repository.UnidadeRepository;

@Service
public class UnidadeService implements ICrudService<Unidade>, IPageService<Unidade> {

    private final UnidadeRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nome"));

    public UnidadeService(UnidadeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Unidade> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    public Page<Unidade> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    public Unidade consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Unidade salvar(Unidade objeto) {
        return repo.save(objeto);
    }

    @Override
    public void remover(Long id) {
        repo.deleteById(id);        
    }
    
}
