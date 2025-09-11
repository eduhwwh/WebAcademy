package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@Service
public class UsuarioService implements ICrudService<Usuario>, IPageService<Usuario> {

    private final UsuarioRepository repo;

    private static final Pageable PAGINACAO = Pageable.unpaged(
        Sort.by(Sort.Direction.ASC, "nomeCompleto"));

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Usuario> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    public Page<Usuario> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    public Usuario consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario salvar(Usuario objeto) {
        if (objeto.getSenha() == null || objeto.getSenha().isBlank()) {
            var id = objeto.getId();
            var usuario = consultar(id);
            if (usuario != null) {
                objeto.setSenha(usuario.getSenha());
            }
        }
        return repo.save(objeto);
    }

    @Override
    public void remover(Long id) {
        repo.deleteById(id);
    }

    public Usuario consultarPorNomeUsuario(String nomeUsuario) {
        return repo.findByNomeUsuario(nomeUsuario);
    }
    
}
