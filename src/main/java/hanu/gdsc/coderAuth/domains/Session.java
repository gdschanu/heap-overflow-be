package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import hanu.gdsc.share.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Session extends IdentifiedDomainObject{
    private Id coderId;
    private DateTime expireAt; 

    private Session(Id id, Id coderId, DateTime expireAt) {
        super(id);
        this.coderId = coderId;
        this.expireAt = expireAt;
    }

    public static Session createSession(Id id, Id coderId) {
        return new Session(
                id,
                coderId,
                DateTime.now().plusMinutes(10080));
    }

    public String genToken(String secret) {
        return Jwts.builder()
                .setId(getId().toString())
                .setExpiration(Date.from(getExpireAt().toZonedDateTime().toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public static Id verifyTokenAndGetSessionId(String token, String secret) throws UnauthorizedException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return new Id(claims.getId());
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid token");
        }
    }

    public Id getCoderId() {
        return coderId;
    }

    public DateTime getExpireAt() {
        return expireAt;
    }

    public boolean invalidated() {
        if(expireAt.isBefore(DateTime.now())) {
            return true;
        }
        return false;
    }
}
