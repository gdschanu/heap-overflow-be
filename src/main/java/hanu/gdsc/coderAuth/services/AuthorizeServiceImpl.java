package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.errors.ExpiredToken;
import hanu.gdsc.coderAuth.errors.UnconfirmedEmail;
import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import io.jsonwebtoken.Claims;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private GetClaimFromToken getClaimFromToken;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Id authorize(String token) {
        Claims claims = getClaimFromToken.getClaims(token);
        Id sessionId = new Id(claims.getId());
        Session session = sessionRepository.getById(sessionId);
        if (session.invalidate()) {
            throw new ExpiredToken();
        }
        Id coderId = session.getCoderId();
        User user = userRepository.getByCoderId(coderId);
        if (user.isRegistrationConfirmed()) {
            throw new UnconfirmedEmail();
        }
        return session.getCoderId();
    }

    @Override
    public Id authorizeUnconfirmedRegistration(String token) {
        Claims claims = getClaimFromToken.getClaims(token);
        Id sessionId = new Id(claims.getId());
        Session session = sessionRepository.getById(sessionId);
        if (session.invalidate()) {
            throw new ExpiredToken();
        }
        return session.getCoderId();
    }
}
