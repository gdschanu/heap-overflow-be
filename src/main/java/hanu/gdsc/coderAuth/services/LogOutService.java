package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.config.SessionConfig;
import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogOutService {
    private final String secretKey = SessionConfig.TOKEN_SECRET;
    @Autowired
    private SessionRepository sessionRepository;

    public void logOut(String token) throws UnauthorizedException, InvalidInputException {
        Id sessionId = Session.verifyTokenAndGetSessionId(token, secretKey);
        sessionRepository.deleteById(sessionId);
    }
}
