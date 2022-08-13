package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.*;
import hanu.gdsc.coderAuth.errors.NonExistentUsernameOrEmail;
import hanu.gdsc.coderAuth.errors.WrongPassword;
import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogInServiceImpl implements LogInService {
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SessionRepository sessionRepository;

   @Override
   public Output logInService(String usernameOrEmail, String password) {
      User user;
      if (Email.isValidEmail(usernameOrEmail)) {
         Email email = new Email(usernameOrEmail);
         user = userRepository.getByEmail(email);
      } else {
         Username username = new Username(usernameOrEmail);
         user = userRepository.getByUsername(username); 
      }
      Id coderId = null;
      if (user != null) {
         coderId = user.getId();
      } else {
         throw new NonExistentUsernameOrEmail();
      }
      if (user.getPassword().toHashedPasswordString().equals(HashedPassword.fromRawPassword(password).toHashedPasswordString())) {
         String token = createToken(coderId);
         return Output.builder()
         .username(user.getUsername().toString())
                 .coderId(coderId.toString())
                 .token(token)
         .build();
      } else {
         throw new WrongPassword();
      }
   }

   private final String secretKey = "Hanuoj";
   private String createToken(Id coderId) {
      Id sessionId = Id.generateRandom();
      Session session = Session.createSession(sessionId, coderId);
      sessionRepository.save(session);
      return Jwts.builder()
              .setId(sessionId.toString())
              .setExpiration(Date.from(session.getExpireAt().toZonedDateTime().toInstant()))
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
   }
}