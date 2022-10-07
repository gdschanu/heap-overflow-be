package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class LogOutService {

    @Autowired
    private GetClaimFromToken getClaimFromToken;

    @Autowired
    private SessionRepository sessionRepository;

    public void logOut(String token) {
       Id sessionId = new Id(getClaimFromToken.getClaims(token).getId());
       sessionRepository.deleteById(sessionId);
    }
}
