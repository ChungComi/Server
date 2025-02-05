package ChungComiServer.dot.global.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${SECRET_KEY}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Long id){
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("expirationTime",86400000)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            log.info("Token successfully validated, token's subject: {}", claims.getSubject());
            return claims.getSubject();
        }catch (JwtException | IllegalArgumentException e){
            throw new SecurityException("Invalid JWT token",e);
        }
    }
}
