package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.errors.NonExistentUsernameOrEmail;
import hanu.gdsc.coderAuth.errors.WrongPassword;
import hanu.gdsc.coderAuth.repositories.SessionRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.Id;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LogInServiceImpl implements LogInService {
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SessionRepository sessionRepository;

   @Override
   public String logInService(String usernameOrEmail, String password) {
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
         return createToken(coderId);
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
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
   }
}