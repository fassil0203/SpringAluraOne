package med.voll.api.infra.exception.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public  String gerarToken (Usuario usuario){
        try {
            var algoritmo= Algorithm.HMAC256(secret);   // e recomendado gerar data de expiracap p/ os tokens
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao Gerar token JWT",exception);
        }
    }
    public String getSubject(String tokenJWT){          //Metodo que Valida o Token
        try {
            var algoritmo= Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                   .withIssuer("API Voll.med")
                   .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("token JWT Invalido ou Expiraqdo !!!") ;
        }

    }


    private Instant dataExpiracao() {      //gerando tempo de expiracao do token
        return  LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
