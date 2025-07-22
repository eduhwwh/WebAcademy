package br.ufac.sgcm.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EspecialidadeController {
    EspecialidadeDao eDao;

    public EspecialidadeController() {
        eDao = new EspecialidadeDao();
    }

    public List<Especialidade> get() {
        return eDao.get();
    }

    public List<Especialidade> get(String termoBusca) {
        return eDao.get(termoBusca);
    }

    public Especialidade get(Long id) {
        return eDao.get(id);
    }

    public int insert(Especialidade objeto) {
        return eDao.insert(objeto);
    }

    public int update(Especialidade objeto) {
        return eDao.update(objeto);
    }

    public int delete(Especialidade objeto) {
        return eDao.delete(objeto);
    }

    // Método que usa servlet
    public List<Especialidade> processList(HttpServletRequest req, HttpServletResponse res) {
        List<Especialidade> registros = new ArrayList<>();

        String paramExcluir = req.getParameter("excluir");
        if(paramExcluir != null && !paramExcluir.isEmpty()){
            Long id = Long.parseLong(paramExcluir);
            Especialidade objeto = this.get(id);
            this.delete(objeto);
        }
        registros = this.get();


        
        return registros;
    }

    public Especialidade processRequest(HttpServletRequest req, HttpServletResponse res) {
        Especialidade objeto = new Especialidade();
        String paramSubmit = req.getParameter("submit");
        String paramNome = req.getParameter("nome");
        objeto.setNome(paramNome);
        

        if(paramSubmit !=null && !paramSubmit.isEmpty()){
            String paramNome = req.getParameter("nome");
            objeto.setNome(paramNome);
                    
            this.insert(objeto);
            try {
                res.sendRedirect("especialiadade.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objeto;
    }
}
