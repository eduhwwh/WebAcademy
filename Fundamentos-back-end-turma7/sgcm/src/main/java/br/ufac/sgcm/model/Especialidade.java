package br.ufac.sgcm.model;

import java.io.Serializable;

public class Especialidade implements Serializable {
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.nome;
    }

}
