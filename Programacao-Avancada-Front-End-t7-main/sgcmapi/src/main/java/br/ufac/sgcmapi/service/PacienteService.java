package br.ufac.sgcmapi.service;

import java.util.List;

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
    public List<Paciente> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    public Page<Paciente> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    public Paciente consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Paciente salvar(Paciente objeto) {
        return repo.save(objeto);
    }

    @Override
    public void remover(Long id) {
        repo.deleteById(id);
    }
    
}
