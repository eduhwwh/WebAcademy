package br.ufac.sgcmapi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Seguranca {

    private final PerfilUsuarioService perfilUsuarioService;

    public Seguranca(PerfilUsuarioService perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    UserDetailsService udService() {
        return perfilUsuarioService;
    }

    @Bean
    BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider() {
        var provider = new DaoAuthenticationProvider(udService());
        provider.setPasswordEncoder(passEncoder());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authProvider());
        http.sessionManagement(
            sessao -> sessao.sessionCreationPolicy((SessionCreationPolicy.STATELESS))
        );

        http.authorizeHttpRequests(autorizacao -> autorizacao
            .requestMatchers(HttpMethod.POST, "/login/autenticar").permitAll()
            .requestMatchers("/config/**").hasRole("ADMIN")
            .anyRequest().authenticated());

        return http.build();
    }
    
}
