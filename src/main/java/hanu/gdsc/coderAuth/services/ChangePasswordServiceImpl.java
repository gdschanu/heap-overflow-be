package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.errors.WrongPassword;
import hanu.gdsc.coderAuth.repositories.SessionRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private GetClaimFromToken getClaimFromToken;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void changePassword(String token, String oldPassword, String newPassword) {
        Id sessionId = new Id(getClaimFromToken.getClaims(token).getId());
        Session session = sessionRepository.getById(sessionId);
        Id coderId = session.getCoderId();

        User user = userRepository.getByCoderId(coderId);
        if(!user.getPassword().toHashedPasswordString().equals(HashedPassword.fromRawPassword(oldPassword).toHashedPasswordString())) {
            throw new WrongPassword();
        }
        else {
            user.setPassword(HashedPassword.fromRawPassword(newPassword));
            sessionRepository.deleteSession(coderId, sessionId);
        }    
    }
}


