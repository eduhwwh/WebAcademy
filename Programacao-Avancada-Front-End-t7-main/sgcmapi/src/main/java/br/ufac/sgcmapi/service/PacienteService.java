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

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@Service
public class PacienteService implements ICrudService<Paciente>, IPageService<Paciente> {

    private final PacienteRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nome"));

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "pacientes",
        key = "'todos'",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public List<Paciente> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    @Cacheable(
        value = "pacientes",
        key = "'paginado' + '-page:' + #paginacao.pageNumber + '-size:' + #paginacao.pageSize + '-sort:' + #paginacao.sort.toString()",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Paciente> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    @Cacheable(value = "paciente", unless = "#result == null")
    public Paciente consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "paciente", key = "#objeto.id"),
        @CacheEvict(value = "pacientes", allEntries = true)
    })
    public Paciente salvar(Paciente objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "paciente", key = "#id"),
        @CacheEvict(value = "pacientes", allEntries = true)
    })
    public void remover(Long id) {
        repo.deleteById(id);
    }
    
}
