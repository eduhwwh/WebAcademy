package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Especialidade;

public class EspecialidadeDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conexao;

    public EspecialidadeDao() {
        conexao = new ConexaoDB().getConexao();
    }

    // Metodo para retornar uma lista de especialidade
    public List<Especialidade> get() {

        String sql = "SELECT * FROM especialidade ";

        List<Especialidade> registros = new ArrayList<>();
        try {

            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Especialidade objeto = new Especialidade();
                objeto.setId(rs.getLong("id"));
                objeto.setNome(rs.getString("nome"));
                registros.add(objeto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros;

    }

    // Metodo para inserir uma especialidade

    public int insert(Especialidade objeto) {
        String sql = "INSERT INTO especialidade (nome) VALUES (?)";
        int registrosAfetados = 0;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    // Metodo para atualizar

    public int update(Especialidade objeto){

        int registrosAfetados = 0;

        String sql = "UPDATE especialidade SET nome=? WHERE id=?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setLong(2,objeto.getId());
            ps.executeUpdate();

            registrosAfetados = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    // metodo para excluir

    public int delete (Especialidade objeto){


        String sql = "DELETE  FROM especialidade WHERE id=?";
        int registrosAfetados = 0;

        try{
            ps = conexao.prepareStatement(sql);
            // ps.setString(1, objeto.getNome());
            ps.setLong(1,objeto.getId());
            ps.executeUpdate();

            registrosAfetados = ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return registrosAfetados;
    }


}
