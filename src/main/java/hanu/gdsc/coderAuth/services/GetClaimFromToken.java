package hanu.gdsc.coderAuth.services;

import hanu.gdsc.share.error.UnauthorizedError;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.errors.InvalidToken;
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
            throw new UnauthorizedError("Invalid token");
        }
    }
}
