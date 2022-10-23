package hanu.gdsc.domain.coderAuth.services;

import hanu.gdsc.domain.coderAuth.config.SessionConfig;
import hanu.gdsc.domain.coderAuth.models.HashedPassword;
import hanu.gdsc.domain.coderAuth.models.Session;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.exceptions.WrongPasswordException;
import hanu.gdsc.domain.coderAuth.repositories.SessionRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionConfig sessionConfig;

    public void changePassword(String token, String oldPassword, String newPassword) throws InvalidInputException, WrongPasswordException, UnauthorizedException {
        Id sessionId = Session.verifyTokenAndGetSessionId(token, sessionConfig.getTokenSecret());
        Session session = sessionRepository.getById(sessionId);
        Id coderId = session.getCoderId();

        User user = userRepository.getByCoderId(coderId);
        if (!user.getPassword().toHashedPasswordString().equals(HashedPassword.fromRawPassword(oldPassword).toHashedPasswordString())) {
            throw new WrongPasswordException();
        } else {
            user.setPassword(HashedPassword.fromRawPassword(newPassword));
            sessionRepository.deleteSession(coderId, sessionId);
        }
    }
}


