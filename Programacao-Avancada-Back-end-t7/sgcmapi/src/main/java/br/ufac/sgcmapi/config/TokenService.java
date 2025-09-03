package br.ufac.sgcmapi.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.ufac.sgcmapi.model.Usuario;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private Instant gerarDataExpiracao() {
        var dateTime = LocalDateTime.now().plusMinutes(60);
        var zoneId = ZoneId.systemDefault();
        var zoneDateTime = dateTime.atZone(zoneId);
        return zoneDateTime.toInstant();
    }

    public String criarToken(Usuario usuario) {
        var segredo = Algorithm.HMAC256(secret);
        var token = JWT.create()
            .withIssuer("SGCM")
            .withSubject(usuario.getNomeUsuario())
            .withClaim("nomeCompleto", usuario.getNomeCompleto())
            .withClaim("papel", usuario.getPapel().name())
            .withClaim("dataLimiteRenovacao", LocalDate.now().toString())
            .withExpiresAt(gerarDataExpiracao())
            .sign(segredo);
        
        return token;
    }

    public String validarToken(String token) {
        var segredo = Algorithm.HMAC256(secret);
        var tokenValidado = JWT.require(segredo)
            .withIssuer("SGCM")
            .build()
            .verify(token);
        
        return tokenValidado.getSubject();
    }

    public boolean isDataLimiteRenovacaoExpirada(DecodedJWT token) {
        var claimDataLimite = token.getClaim("dataLimiteRenovacao");
        var dataLimite = LocalDate.parse(claimDataLimite.asString());
        var hoje = LocalDate.now();
        return hoje.isAfter(dataLimite);
    }
    
}
