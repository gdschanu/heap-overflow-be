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
    public void authorize(String token) {
        if (token == null) {
            throw new BusinessLogicError("Chưa login", "NULL_TOKEN");
        } else {
            Claims claims = getClaims(token);
            if (claims == null) {
                throw new BusinessLogicError("Token không tồn tại", "WRONG_TOKEN");
            }
            if (!isNotExpired(token)) {
                throw new BusinessLogicError("Token đã hết hiệu lực", "EXPIRED_TOKEN");
            }
            if (!isRegistrationConfirmed(token)) {
                throw new BusinessLogicError("Chưa xác nhận email", "UNCONFIRMED_TOKEN");
            }
        }
    }

    private final String secretKey = "Hanuoj";

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public Id getId(String token) {
        return new Id(getClaims(token).getId());
    }

    public boolean isNotExpired(String token) {
        Session session = sessionRepository.getById(getId(token));
        DateTime time = new DateTime(DateTime.now().toString());
        return time.isBefore(session.getExpiredAt());
    }

    public boolean isRegistrationConfirmed(String token) {
        Session session = sessionRepository.getById(getId(token));
        User user = userRepository.getByCoderId(session.getCoderId());
        return user.isRegistrationConfirmed();
    }
}
