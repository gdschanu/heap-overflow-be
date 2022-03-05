package hanu.gdsc.coderAuth.services.logIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.JPA.UserRepository;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class LogIn {
   @Autowired
   private UserRepository repository;

   public void checkUserInformation(String usernameOrEmail, String password) {
      User user=null;
      if(repository.getByUsername(usernameOrEmail)!=null){
         user= repository.getByUsername(usernameOrEmail);
      } else if(repository.getByEmail(usernameOrEmail)!=null) {
         user =repository.getByEmail(usernameOrEmail);
      } else {
         throw new BusinessLogicError("Username/email không hợp lệ");
      }
      if(user.getPassword()==password){
         AccessTokenGenerator generator = new AccessTokenGenerator();
         generator.createToken();
      } else {
         throw new BusinessLogicError("Sai mật khẩu");
      }
   }
}
