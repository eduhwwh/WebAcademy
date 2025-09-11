package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.model.RespostaAns;
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
    public List<Convenio> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    public Page<Convenio> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    public Convenio consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Convenio salvar(Convenio objeto) {
        return repo.save(objeto);
    }

    @Override
    public void remover(Long id) {
        repo.deleteById(id);
    }

    public List<Convenio> consultarAtivos() {
        return repo.findByAtivo(true);
    }

    @Scheduled(fixedDelay = 30000)
    public void verificarRegistroAns() {
        var url = "https://www.ans.gov.br/operadoras-entity/v1/operadoras";
        var convenios = consultar("");
        convenios.forEach(convenio -> {
            var cnpjSemFormatacao = convenio.getCnpj().replaceAll("[^0-9]", "");
            var cliente = RestClient.create();
            var resultado = cliente.get()
                .uri(url + "?cnpj=" + cnpjSemFormatacao)
                .retrieve()
                .body(RespostaAns.class);
            if (!resultado.content().isEmpty()) {
                var item = resultado.content().get(0);
                System.out.println(convenio.getNome() + " - " + item.ativa());
                convenio.setAtivo(item.ativa());
                salvar(convenio);
            }
        });
    }
    
}
