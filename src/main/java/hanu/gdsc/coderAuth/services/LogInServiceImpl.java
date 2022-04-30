package hanu.gdsc.coderAuth.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.SessionRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
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
         coderId = user.getCoderId();
      } else {
         throw new BusinessLogicError("Username/email does not exist", "NON-EXISTENT_USERNAME_OR_EMAIL");
      }
      MessageDigest md;
      try {
         md = MessageDigest.getInstance("MD5");
         md.update(password.getBytes());
         byte[] digest = md.digest();
         String myHash = DatatypeConverter
               .printHexBinary(digest).toUpperCase();
         if (user.getPassword().toString().equals(myHash)) {
            return createToken(coderId);
         } else {
            throw new BusinessLogicError("Password is wrong", "WRONG_PASSWORD");
         }
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
         throw new BusinessLogicError("No such algorithm exception", "");
      }
   }

   private final String secretKey = "Hanuoj";

   public String createToken(Id coderId) {
      Id sessionId = Id.generateRandom();
      DateTime expireAt = DateTime.now().plusMinutes(15);
      Session session = new Session(sessionId, coderId, expireAt);
      sessionRepository.save(session);
      return Jwts.builder()
            .setId(sessionId.toString())
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
   }
}