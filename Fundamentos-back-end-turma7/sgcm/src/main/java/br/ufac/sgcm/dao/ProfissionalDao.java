package br.ufac.sgcm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Profissional;
import br.ufac.sgcm.model.Unidade;

public class ProfissionalDao implements InterfaceDao<Profissional> {

    Especialidade eDao;
    UnidadeDao uDao;
    public ProfissionalDao(){
        eDao = new Especialidade();
        uDao = new UnidadeDao();
    }

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
                Especialidade esp = eDao.get(rs.getLong("Especialidade_id"));
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

}
