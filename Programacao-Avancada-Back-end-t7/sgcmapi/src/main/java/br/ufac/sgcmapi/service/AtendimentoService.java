package br.ufac.sgcmapi.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements ICrudService<Atendimento>, IPageService<Atendimento> {

    private final AtendimentoRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.DESC, "data")
        .and(Sort.by(Sort.Direction.ASC, "hora"))
    );

    public AtendimentoService(AtendimentoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Atendimento> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), null, PAGINACAO).getContent();
    }

    public List<Atendimento> consultar(String termoBusca, List<EStatus> status) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), status, PAGINACAO).getContent();
    }

    @Override
    public Page<Atendimento> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), null, paginacao);
    }

    public Page<Atendimento> consultar(String termoBusca, List<EStatus> status, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), status, paginacao);
    }

    @Override
    @Cacheable(value = "atendimento", unless = "#result == null")
    public Atendimento consultar(Long id) {
        System.out.println("Consultando atendimento " + id + " sem cache.");
        return repo.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = "atendimento", key = "#objeto.id")
    public Atendimento salvar(Atendimento objeto) {
        System.out.println("Salvando atendimento.");
        return repo.save(objeto);
    }

    @Override
    public void remover(Long id) {
        var registro = this.consultar(id);
        if (registro != null) {
            registro.setStatus(EStatus.CANCELADO);
            this.salvar(registro);
        }
    }

    public Atendimento atualizarStatus(Long id) {
        var registro = this.consultar(id);
        if (registro != null) {
            var novoStatus = registro.getStatus().proximo();
            registro.setStatus(novoStatus);
            registro = this.salvar(registro);
        }
        return registro;
    }

    public List<LocalTime> consultarHorariosOcupadosProfissional(Long id, LocalDate data) {
        return repo.consultarHorariosOcupadosProfissional(id, data);
    }

    public List<LocalTime> consultarHorariosOcupadosPaciente(Long id, LocalDate data) {
        return repo.consultarHorariosOcupadosPaciente(id, data);
    }
    
}
