package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.*;
import hanu.gdsc.coderAuth.exceptions.NonExistentUsernameOrEmailException;
import hanu.gdsc.coderAuth.exceptions.WrongPasswordException;
import hanu.gdsc.coderAuth.repositories.session.SessionRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogInService {
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SessionRepository sessionRepository;

   @Builder
   public static class Output {
      public String username;
      public String coderId;
      public String token;
   }

   public Output logInService(String usernameOrEmail, String password) throws NonExistentUsernameOrEmailException, InvalidInputException, WrongPasswordException {
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
         throw new NonExistentUsernameOrEmailException();
      }
      if (user.getPassword().toHashedPasswordString().equals(HashedPassword.fromRawPassword(password).toHashedPasswordString())) {
         String token = createToken(coderId);
         return Output.builder()
         .username(user.getUsername().toString())
                 .coderId(coderId.toString())
                 .token(token)
         .build();
      } else {
         throw new WrongPasswordException();
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