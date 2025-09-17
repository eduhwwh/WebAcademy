package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Cacheable(
        value = "usuarios",
        key = "'todos'",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public List<Usuario> consultar(String termoBusca) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), PAGINACAO).getContent();
    }

    @Override
    @Cacheable(
        value = "usuarios",
        key = "'paginado' + '-page:' + #paginacao.pageNumber + '-size:' + #paginacao.pageSize + '-sort:' + #paginacao.sort.toString()",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Usuario> consultar(String termoBusca, Pageable paginacao) {
        return repo.consultar(StringUtils.trimAllWhitespace(termoBusca), paginacao);
    }

    @Override
    @Cacheable(value = "usuario", unless = "#result == null")
    public Usuario consultar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "usuario", key = "#objeto.id"),
        @CacheEvict(value = "usuarios", allEntries = true)
    })
    public Usuario salvar(Usuario objeto) {
        if (objeto.getSenha() == null || objeto.getSenha().isBlank()) {
            var usuario = this.consultar(objeto.getId());
            if (usuario != null) {
                objeto.setSenha(usuario.getSenha());
            }
        } else {
            var encoder = new BCryptPasswordEncoder();
            var senhaCriptografada = encoder.encode(objeto.getSenha());
            objeto.setSenha(senhaCriptografada);
        }
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "usuario", key = "#id"),
        @CacheEvict(value = "usuarios", allEntries = true)
    })
    public void remover(Long id) {
        repo.deleteById(id);
    }

    public Usuario consultarPorNomeUsuario(String nomeUsuario) {
        return repo.findByNomeUsuario(nomeUsuario);
    }
    
}
