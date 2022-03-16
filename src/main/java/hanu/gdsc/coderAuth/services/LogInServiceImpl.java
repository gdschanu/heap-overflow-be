package hanu.gdsc.coderAuth.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
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
public class LogInServiceImpl implements LogInService{
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SessionRepository sessionRepository;
   
   @Override
   public String checkUserInformation(Object usernameOrEmail, Password password) {
      User user;
      if(Email.isValidEmail(usernameOrEmail.toString())) {
         Email email = (Email)usernameOrEmail;
         user = userRepository.getByEmail(email);
      } else {
         Username username = (Username)usernameOrEmail;
         user = userRepository.getByUsername(username);
      }

      Id coderId = user.getCoderId();
      if (BCrypt.checkpw(password.toString(), user.getPassword().toString())) {
         return createToken(coderId);
      } else {
         throw new BusinessLogicError("Sai mật khẩu.", "WRONG_PASSWORD");
      }
   }

   @Override
   public String createToken(Id coderId) {
      Session session = new Session(Id.generateRandom(), coderId, DateTime.now().plusMinutes(15));
      sessionRepository.save(session);
      return Jwts.builder()
              .setId(session.getId().toString())
              .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode("Hanuoj<Secretkey>".getBytes()))
              .compact(); 
  }

   @Override
   public Object usernameOrEmail(User user) {    
      if(user.getUsername() != null) {
         return user.getUsername();
      } else {
         return user.getEmail();
      }
   }

}
