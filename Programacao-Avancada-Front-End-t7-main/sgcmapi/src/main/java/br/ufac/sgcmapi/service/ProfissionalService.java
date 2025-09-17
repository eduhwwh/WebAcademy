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

import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.ProfissionalRepository;

@Service
public class ProfissionalService implements ICrudService<Profissional>, IPageService<Profissional> {

    private final ProfissionalRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nome"));

    public ProfissionalService(ProfissionalRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "profissionais",
        key = "'todos'",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public List<Profissional> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    @Cacheable(
        value = "profissionais",
        key = "'paginado' + '-page:' + #paginacao.pageNumber + '-size:' + #paginacao.pageSize + '-sort:' + #paginacao.sort.toString()",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Profissional> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    @Cacheable(value = "profissional", unless = "#result == null")
    public Profissional consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "profissional", key = "#objeto.id"),
        @CacheEvict(value = "profissionais", allEntries = true)
    })
    public Profissional salvar(Profissional objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "profissional", key = "#id"),
        @CacheEvict(value = "profissionais", allEntries = true)
    })
    public void remover(Long id) {
        repo.deleteById(id);
    }
    
}
