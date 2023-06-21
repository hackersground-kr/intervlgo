package filter;

import auth.PrincipalDetails;
import auth.PrincipalDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtProvider {
    private final PrincipalDetailsService principalDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    private final long VALID_MILLISECOND = 1000L *60 * 30;

    private Key getSecretKey(String secret){
        byte[] KeyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String getId(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey(secret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public String generateToken(String id){
        Date date = new Date();

        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+VALID_MILLISECOND))
                .signWith(getSecretKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey(secret))
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }


    public UsernamePasswordAuthenticationToken getAuthentication(String jwtToken) {
        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(getId(jwtToken));
        return new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getPassword(), principalDetails.getAuthorities());
    }
}
