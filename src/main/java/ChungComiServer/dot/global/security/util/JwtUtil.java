package ChungComiServer.dot.global.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
public class JwtUtil {

    private final Key key;
    private final long EXPIRATION_TIME;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .claim("expirationTime",EXPIRATION_TIME)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }catch (JwtException | IllegalArgumentException e){
            throw new SecurityException("Invalid JWT token",e);
        }
    }
}
