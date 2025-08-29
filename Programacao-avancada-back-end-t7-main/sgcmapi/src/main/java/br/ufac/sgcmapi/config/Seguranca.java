package br.ufac.sgcmapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Seguranca  {

    private final PerfilUsuarioService perfilUsuarioService;

    public Seguranca(PerfilUsuarioService perfilUsuarioService){
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @Bean
    AuthenticationManager authMenager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        var provider = new DaoAuthenticationProvider(udService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
        
    }
    
}
