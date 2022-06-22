package hanu.gdsc.coderAuth_coderAuth.services;

import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth_coderAuth.errors.InvalidToken;
import hanu.gdsc.share.error.UnauthorizedError;
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
            throw new UnauthorizedError();
        }
    }
}
