package hanu.gdsc.domain.coderAuth.services.access;

import hanu.gdsc.domain.coderAuth.config.SessionConfig;
import hanu.gdsc.domain.coderAuth.models.Session;
import hanu.gdsc.domain.coderAuth.repositories.SessionRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogOutService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionConfig sessionConfig;

    public void logOut(String token) throws UnauthorizedException, InvalidInputException {
        Id sessionId = Session.verifyTokenAndGetSessionId(token, sessionConfig.getTokenSecret());
        sessionRepository.deleteById(sessionId);
    }
}
