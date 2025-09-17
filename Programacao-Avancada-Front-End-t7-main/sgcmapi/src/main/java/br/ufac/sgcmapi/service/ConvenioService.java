package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@Service
public class ConvenioService implements ICrudService<Convenio>, IPageService<Convenio> {

    private final ConvenioRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nome"));

    public ConvenioService(ConvenioRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "convenios",
        key = "'todos'",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public List<Convenio> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    @Cacheable(
        value = "convenios",
        key = "'paginado' + '-page:' + #paginacao.pageNumber + '-size:' + #paginacao.pageSize + '-sort:' + #paginacao.sort.toString()",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Convenio> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    @Cacheable(value = "convenio", unless = "#result == null")
    public Convenio consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "convenio", key = "#objeto.id"),
        @CacheEvict(value = "convenios", allEntries = true)
    })
    public Convenio salvar(Convenio objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "convenio", key = "#id"),
        @CacheEvict(value = "convenios", allEntries = true)
    })
    public void remover(Long id) {
        repo.deleteById(id);
    }

    @Cacheable(value = "convenios", key = "'ativos'")
    public List<Convenio> consultarAtivos() {
        return repo.findByAtivo(true);
    }
    
}
