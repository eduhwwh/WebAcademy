package br.ufac.sgcmapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;

import br.ufac.sgcmapi.config.PerfilUsuario;
import br.ufac.sgcmapi.config.TokenService;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public LoginController(
            AuthenticationManager authManager,
            UsuarioService usuarioService,
            TokenService tokenService) {
        this.authManager = authManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticar(@RequestBody Usuario usuario) {
        var loginToken = new UsernamePasswordAuthenticationToken(usuario.getNomeUsuario(), usuario.getSenha());
        var autenticacao = authManager.authenticate(loginToken);
        var principal = (PerfilUsuario) autenticacao.getPrincipal();

        var usuarioAutenticado = usuarioService.consultarPorNomeUsuario(principal.getUsername());
        var token = tokenService.criarToken(usuarioAutenticado);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/renovar")
    public ResponseEntity<String> renovar(
            @RequestHeader("Authorization") String authHeader) {

        var token = authHeader.replace("Bearer ", "");
        var tokenDecodificado = JWT.decode(token);

        if (tokenService.isDataLimiteRenovacaoExpirada(tokenDecodificado)) {
            var mensagem = "Data limite de renovação expirada!";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mensagem);
        }

        var nomeUsuario = tokenDecodificado.getSubject();
        var usuario = usuarioService.consultarPorNomeUsuario(nomeUsuario);
        var tokenNovo = tokenService.criarToken(usuario);

        return ResponseEntity.ok(tokenNovo);
    }
    
}
