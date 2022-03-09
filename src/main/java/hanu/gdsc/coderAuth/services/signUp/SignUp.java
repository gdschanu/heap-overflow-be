package hanu.gdsc.coderAuth.services.signUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.JPA.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SignUp {
    @Autowired
    private UserRepository repository;

    public void signUpSuccess(String username, String email, String password, Id coderId) {
        if(repository.getByUsername(username)==null|| repository.getByEmail(email)==null){
            User user= new User(username, email, password, coderId);
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    		user.setPassword(encoder.encode(user.getPassword()));

            new Password(password);
            new Username(username);

            user.setRegistrationConfirmed(false);
            repository.save(user);
        } else{
            throw new BusinessLogicError("Username/email đã tồn tại");
        }
    }
}
