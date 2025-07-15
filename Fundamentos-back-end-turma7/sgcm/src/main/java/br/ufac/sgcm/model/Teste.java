package br.ufac.sgcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.ConexaoDB;

public class Teste {
    public static void main(String[] args) throws Exception {
        Especialidade e1 = new Especialidade();
        e1.setNome("Cardiologia");
        System.out.println(e1.getNome());


        ConexaoDB conexao = new ConexaoDB();
        Connection instancia = conexao.getConexao();

        if(instancia != null){
            System.out.println("Conexao realizada com sucesso!");
        }else{
            System.out.println("Falha na conexão");
            
        }

        String sql = "SELECT * FROM especialidade";

        PreparedStatement ps;
        ResultSet rs;

        ps = instancia.prepareStatement(sql)

        rs = ps.executeQuery();
        List<Especialidade> lista = new ArrayList<>();

        while(rs.next()){
            Especialidade obj = new Especialidade();
            obj.setId(rs.getLong("id"));
            obj.setNome(rs.getString("nome"));
            lista.add(obj);
        }

        for(Especialidade esp : lista){
            System.out.println(esp.getNome());
        }


    }

}
