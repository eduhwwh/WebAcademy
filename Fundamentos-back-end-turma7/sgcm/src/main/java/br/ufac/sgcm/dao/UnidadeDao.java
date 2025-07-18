package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.ufac.sgcm.model.Unidade;

public class UnidadeDao implements InterfaceDao<Unidade> {

    PreparedStatement ps;
    ResultSet rs;
    Connection conexao;

    public UnidadeDao() {
        conexao = new ConexaoDB().getConexao();
    }

    @Override
    public Unidade get(Long id) {
        Unidade u = new Unidade();
        String sql = "SELECT * FROM unidade WHERE id=?";

        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome")); // Corrigido
                u.setEndereco(rs.getString("endereco"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Unidade> get() {
        return null; // implementar depois
    }

    @Override
    public List<Unidade> get(String termoBusca){
        return null;
    }

    @Override
    public int insert(Unidade objeto){
        return 0;
    }

    @Override
    public int update(Unidade objeto){
        return 0;
    }

    @Override
    public int delete(Unidade objeto){
        return 0;
    }

}
