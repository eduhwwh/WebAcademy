package br.ufac.sgcmapi.service;

import java.util.List;

public interface ICrudService<T> {
    
    public List<T>consultar(String termoBuscar);

        public T consultar(Long id);
        public T salvar(T objeto);
        public void remover(Long id);

    

}
