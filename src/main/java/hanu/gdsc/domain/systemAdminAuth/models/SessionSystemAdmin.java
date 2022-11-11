package hanu.gdsc.domain.systemAdminAuth.models;

import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class SessionSystemAdmin extends IdentifiedDomainObject{
    private Id systemAdminId;
    private DateTime expireAt; 

    private SessionSystemAdmin(Id id, Id systemAdminId, DateTime expireAt) {
        super(id);
        this.systemAdminId = systemAdminId;
        this.expireAt = expireAt;
    }

    public static SessionSystemAdmin createSession(Id id, Id systemAdminId) {
        return new SessionSystemAdmin(
                id,
                systemAdminId,
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

    public Id getSystemAdminId() {
        return systemAdminId;
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
