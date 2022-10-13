package hanu.gdsc.coderAuth.services;

import hanu.gdsc.share.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class GetClaimFromToken {
    public Claims getClaims(String token) throws UnauthorizedException {
        try {
            return Jwts.parser()
                .setSigningKey("Hanuoj".getBytes())
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid token");
        }
    }
}
