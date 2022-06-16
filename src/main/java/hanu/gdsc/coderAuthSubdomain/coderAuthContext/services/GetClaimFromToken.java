package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.InvalidToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class GetClaimFromToken {
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                .setSigningKey("Hanuoj".getBytes())
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new InvalidToken();
        }
    }
}
