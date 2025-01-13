package ChungComiServer.dot.global.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String SECRETE_KEY;

    @Value("${EXPIRATION_TIME}")
    private long EXPIRATION_TIME;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .claim("expirationTime",EXPIRATION_TIME)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRETE_KEY)
                .compact();
    }

    public String validateToken(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRETE_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }catch (JwtException | IllegalArgumentException e){
            throw new SecurityException("Invalid JWT token",e);
        }
    }
}
