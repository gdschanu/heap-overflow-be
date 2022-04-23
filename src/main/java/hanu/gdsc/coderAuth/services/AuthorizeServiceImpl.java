package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.SessionRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Id authorize(String token) {
        if (token == null) {
            throw new BusinessLogicError("You haven't log in yet", "NULL_TOKEN");
        } else {
            Claims claims = getClaims(token);
            if (claims == null) {
                throw new BusinessLogicError("Token doesn't exist", "WRONG_TOKEN");
            }
            if (!isNotExpired(token)) {
                throw new BusinessLogicError("Token is expired", "EXPIRED_TOKEN");
            }
            if (!isRegistrationConfirmed(token)) {
                throw new BusinessLogicError("You haven't confirm your email", "UNCONFIRMED_TOKEN");
            }
            return getSession(token).getCoderId();
        }
    }

    @Override
    public Id authorizeUnconfirmedRegistration(String token) {
        if (token == null) {
            throw new BusinessLogicError("You haven't log in yet", "NULL_TOKEN");
        } else {
            Claims claims = getClaims(token);
            if (claims == null) {
                throw new BusinessLogicError("Token doesn't exist", "WRONG_TOKEN");
            }
            if (!isNotExpired(token)) {
                throw new BusinessLogicError("Token is expired", "EXPIRED_TOKEN");
            }
            return getSession(token).getCoderId();
        }
    }
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey("Hanuoj".getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public Session getSession(String token) {
        Id sessionId = new Id(getClaims(token).getId());
        return sessionRepository.getById(sessionId);
    }

    public boolean isNotExpired(String token) {
        Session session = getSession(token);
        DateTime time = new DateTime(DateTime.now().toString());
        return time.isBefore(session.getExpireAt());
    }

    public boolean isRegistrationConfirmed(String token) {
        Id coderId = getSession(token).getCoderId();
        User user = userRepository.getByCoderId(coderId);
        return user.isRegistrationConfirmed();
    }
}
