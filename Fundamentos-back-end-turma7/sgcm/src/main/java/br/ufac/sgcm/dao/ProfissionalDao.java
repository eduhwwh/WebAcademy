package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Profissional;
import br.ufac.sgcm.model.Unidade;

public class ProfissionalDao implements InterfaceDao<Profissional> {

    private Connection conexao;
    private EspecialidadeDao eDao;
    private UnidadeDao uDao;

    public ProfissionalDao() {
        conexao = new ConexaoDB().getConexao();
        eDao = new EspecialidadeDao(); // DAO, não model
        uDao = new UnidadeDao(); // já pega a conexão internamente
    }

    @Override
    public List<Profissional> get() {
        String sql = "SELECT * FROM profissional";
        List<Profissional> registros = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Profissional objeto = new Profissional();
                objeto.setId(rs.getLong("id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setEmail(rs.getString("email"));
                objeto.setRegistroConselho(rs.getString("registro_conselho"));
                objeto.setTelefone(rs.getString("telefone"));

                Especialidade esp = eDao.get(rs.getLong("especialidade_id")); // cuidado com o nome da coluna
                objeto.setEspecialidade(esp);

                Unidade u = uDao.get(rs.getLong("unidade_id"));
                objeto.setUnidade(u);

                registros.add(objeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros;
    }

    @Override
    public List<Profissional> get(String termoBusca) {
        return null;
    }

    @Override
    public Profissional get(Long id) {
        return null;
    }

    @Override
    public int insert(Profissional objeto) {
        return 0;
    }

    @Override
    public int update(Profissional objeto) {
        return 0;
    }

    @Override
    public int delete(Profissional objeto) {
        return 0;
    }
}
