package hanu.gdsc.coderAuth.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
      if (user != null) {
         Id coderId = user.getCoderId();
         if (BCrypt.checkpw(password, user.getPassword().toString())) {
            return createToken(coderId);
         } else {
            throw new BusinessLogicError("Sai mật khẩu", "WRONG_PASSWORD");
         }
      } else {
         throw new BusinessLogicError("Username/email không tồn tại", "NON-EXISTENT_USERNAME_OR_EMAIL");
      }
   }

   private final String secretKey = "Hanuoj";
   public String createToken(Id coderId) {
      Id id = Id.generateRandom();
      DateTime expireAt = DateTime.now().plusMinutes(15);
      Session session = new Session(id, coderId, expireAt);
      sessionRepository.save(session);
      return Jwts.builder()
            .setId(id.toString())
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
   }
}
