package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.exceptions.WrongPasswordException;
import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private GetClaimFromToken getClaimFromToken;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public void changePassword(String token, String oldPassword, String newPassword) throws InvalidInputException, WrongPasswordException, UnauthorizedException {
        Id sessionId = new Id(getClaimFromToken.getClaims(token).getId());
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


