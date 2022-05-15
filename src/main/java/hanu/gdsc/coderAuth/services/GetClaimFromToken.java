package hanu.gdsc.coderAuth.services;

import org.springframework.stereotype.Service;

import hanu.gdsc.share.error.BusinessLogicError;
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
            throw new BusinessLogicError("Token is invalid", "INVALID_TOKEN");
        }
    }
}
