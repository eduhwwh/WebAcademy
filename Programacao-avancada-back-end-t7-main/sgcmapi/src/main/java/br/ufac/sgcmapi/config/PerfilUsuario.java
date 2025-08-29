package br.ufac.sgcmapi.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufac.sgcmapi.model.Usuario;

public class PerfilUsuario implements UserDetails {

    private final Usuario usuario;

    public PerfilUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var autorizacao = new SimpleGrantedAuthority(usuario.getPapel().name());
        return Arrays.asList(autorizacao);
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getNomeUsuario();
    }

    @Override
    public boolean isEnabled() {
        return usuario.isAtivo();
    }
    
}
