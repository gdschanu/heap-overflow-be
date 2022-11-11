package hanu.gdsc.domain.systemAdminAuth.services.access;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.config.SessionSystemAdminConfig;
import hanu.gdsc.domain.systemAdminAuth.models.SessionSystemAdmin;
import hanu.gdsc.domain.systemAdminAuth.repositories.SessionSystemAdminRepository;
import hanu.gdsc.domain.systemAdminAuth.repositories.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeSystemAdminServiceImpl implements AuthorizeSystemAdminService {
    @Autowired
    private SessionSystemAdminRepository sessionRepository;

    @Autowired
    private SystemAdminRepository systemAdminRepository;

    @Autowired
    private SessionSystemAdminConfig sessionConfig;

    @Override
    public Id authorize(String token) throws UnauthorizedException, InvalidInputException {
        Id sessionId = SessionSystemAdmin.verifyTokenAndGetSessionId(token, sessionConfig.getTokenSecret());
        SessionSystemAdmin session = sessionRepository.getById(sessionId);
        if (session == null || session.invalidated()) {
            throw new UnauthorizedException();
        }
        return session.getSystemAdminId();
    }
}
