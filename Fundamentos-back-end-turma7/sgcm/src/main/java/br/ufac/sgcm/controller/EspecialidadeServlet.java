package br.ufac.sgcm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EspecialidadeServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {

        List<Especialidade> registros = new ArrayList<>();
        EspecialidadeController controler = new EspecialidadeController();
        registros = controler.get();
        res.setContentType("apllication/json");
        res.setCharacterEncoding("utf-8");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(registros);
            PrintWriter saida = res.getWriter();
            
            saida.print(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
