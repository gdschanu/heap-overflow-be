package hanu.gdsc.coderAuth.services.logIn;
import java.util.Base64;

import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.share.domains.DateTime;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccessTokenGenerator {
    //secret key string
    static final String SECRET = "";
    public String createToken() {
        Session session = new Session();
        session.setExpiredAt((DateTime.now()).plusMinutes(15));
        return Jwts.builder()
                .setId(session.getId().toString())
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET.getBytes()))
                .compact(); 
    }
}
