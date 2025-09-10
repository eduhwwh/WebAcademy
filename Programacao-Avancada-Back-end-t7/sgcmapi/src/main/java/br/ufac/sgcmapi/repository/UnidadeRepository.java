package br.ufac.sgcmapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("""
        SELECT u FROM Unidade u
        WHERE :termoBusca IS NULL
        OR u.nome LIKE %:termoBusca%
        OR u.endereco LIKE %:termoBusca%
    """)
    public Page<Unidade> consultar(String termoBusca, Pageable paginacao);
    
}
