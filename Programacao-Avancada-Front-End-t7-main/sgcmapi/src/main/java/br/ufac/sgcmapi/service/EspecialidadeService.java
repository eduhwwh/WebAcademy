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

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService implements ICrudService<Especialidade>, IPageService<Especialidade> {

    private final EspecialidadeRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nome"));

    public EspecialidadeService(EspecialidadeRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "especialidades",
        key = "'todos'",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public List<Especialidade> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    @Cacheable(
        value = "especialidades",
        key = "'paginado' + '-page:' + #paginacao.pageNumber + '-size:' + #paginacao.pageSize + '-sort:' + #paginacao.sort.toString()",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Especialidade> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    @Cacheable(value = "especialidade", unless = "#result == null")
    public Especialidade consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "especialidade", key = "#objeto.id"),
        @CacheEvict(value = "especialidades", allEntries = true)
    })
    public Especialidade salvar(Especialidade objeto) {
       return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "especialidade", key = "#id"),
        @CacheEvict(value = "especialidades", allEntries = true)
    })
    public void remover(Long id) {
        repo.deleteById(id);
    }
    
}
