package br.ufac.sgcm.controller;

import java.util.List;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;

public class EspecialidadeController {

    EspecialidadeDao eDao;

    public EspecialidadeController(){
        eDao = new EspecialidadeController();
    }

    public List<Especialidade> get(){
        return eDao.get();
    }

    public List<Especialidade> get(String termoBusca){
        return eDao.get(termoBusca);
    }

    public Especialidade get(Long id){
        return eDao.get(id);
    }

    public int insert (Especialidade objeto){
        return eDao.insert(objeto);
    }

    public int update (Especialidade objeto){
        return eDao.update(objeto);
    }

    public int delete (Especialidade objeto){
        return eDao.delete(objeto);
    }
}
