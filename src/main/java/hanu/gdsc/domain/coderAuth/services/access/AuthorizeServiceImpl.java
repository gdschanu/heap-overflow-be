package hanu.gdsc.domain.coderAuth.services.access;

import hanu.gdsc.domain.coderAuth.config.SessionConfig;
import hanu.gdsc.domain.coderAuth.models.Session;
import hanu.gdsc.domain.coderAuth.repositories.SessionRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionConfig sessionConfig;

    @Override
    public Id authorize(String token) throws UnauthorizedException, InvalidInputException {
        Id sessionId = Session.verifyTokenAndGetSessionId(token, sessionConfig.getTokenSecret());
        Session session = sessionRepository.getById(sessionId);
        if (session == null || session.invalidated()) {
            throw new UnauthorizedException();
        }
        return session.getCoderId();
    }
}
