package hanu.gdsc.domain.systemAdminAuth.services.access;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.config.SessionSystemAdminConfig;
import hanu.gdsc.domain.systemAdminAuth.exceptions.NonExistentUsernameOrEmailException;
import hanu.gdsc.domain.systemAdminAuth.exceptions.WrongPasswordException;
import hanu.gdsc.domain.systemAdminAuth.models.*;
import hanu.gdsc.domain.systemAdminAuth.repositories.SessionSystemAdminRepository;
import hanu.gdsc.domain.systemAdminAuth.repositories.SystemAdminRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInSystemAdminService {

   @Autowired
   private SessionSystemAdminConfig sessionConfig;
   @Autowired
   private SystemAdminRepository systemAdminRepository;

   @Autowired
   private SessionSystemAdminRepository sessionRepository;

   @Builder
   public static class Output {
      public String username;
      public String systemAdminId;
      public String token;
   }

   public Output logInService(String usernameOrEmail, String password) throws NonExistentUsernameOrEmailException, InvalidInputException, WrongPasswordException {
      SystemAdmin systemAdmin;
      if (Email.isValidEmail(usernameOrEmail)) {
         Email email = new Email(usernameOrEmail);
         systemAdmin = systemAdminRepository.getByEmail(email);
      } else {
         Username username = new Username(usernameOrEmail);
         systemAdmin = systemAdminRepository.getByUsername(username);
      }
      Id systemAdminId = null;
      if (systemAdmin != null) {
         systemAdminId = systemAdmin.getId();
      } else {
         throw new NonExistentUsernameOrEmailException();
      }
      if (systemAdmin.getPassword().toHashedPasswordString().equals(HashedPassword.fromRawPassword(password).toHashedPasswordString())) {
         String token = createToken(systemAdminId);
         return Output.builder()
         .username(systemAdmin.getUsername().toString())
                 .systemAdminId(systemAdminId.toString())
                 .token(token)
         .build();
      } else {
         throw new WrongPasswordException();
      }
   }

   private String createToken(Id systemAdminId) {
      Id sessionId = Id.generateRandom();
      SessionSystemAdmin session = SessionSystemAdmin.createSession(sessionId, systemAdminId);
      sessionRepository.save(session);
      return session.genToken(sessionConfig.getTokenSecret());
   }
}