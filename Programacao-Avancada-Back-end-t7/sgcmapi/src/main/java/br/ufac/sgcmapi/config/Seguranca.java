package br.ufac.sgcmapi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Seguranca {

    private final PerfilUsuarioService perfilUsuarioService;
    private final TokenFilter tokenFilter;

    public Seguranca(
            PerfilUsuarioService perfilUsuarioService,
            TokenFilter tokenFilter) {
        this.perfilUsuarioService = perfilUsuarioService;
        this.tokenFilter = tokenFilter;
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
    @Order(1)
    SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(
            "/v3/api-docs/**",
            "/v3/api-docs*",
            "/swagger-ui/**",
            "/login",
            "default-ui.css");
        http.formLogin(form -> form.defaultSuccessUrl("/swagger-ui/index.html"));
        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authProvider());
        http.authorizeHttpRequests(
            autorizacao -> autorizacao.anyRequest().hasRole("ADMIN")
        );

        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authProvider());
        http.sessionManagement(
            sessao -> sessao.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(
            autorizacao -> autorizacao
                .requestMatchers(HttpMethod.POST, "/login/autenticar").permitAll()
                .requestMatchers("/config/**").hasRole("ADMIN")
                .anyRequest().authenticated());
        
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
}
