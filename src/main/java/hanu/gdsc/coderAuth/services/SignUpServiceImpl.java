package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hanu.gdsc.coder.services.CreateCoderService;
import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateCoderService createCoderService;

    @Override
    public boolean signUp(Email email, Username username, Password password) {
        if(userRepository.getByUsername(username)==null && userRepository.getByEmail(email)==null){
            boolean registrationConfirmed = false;
            User user = new User(Id.generateRandom(), email, username, 
                password, createCoderService.create(), registrationConfirmed);

            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    		user.setPassword(new Password(encoder.encode(password.toString()))); 
            userRepository.save(user);
            return true;
        } else{
            throw new BusinessLogicError("Username/email đã tồn tại", "EXISTED");
        }
    }
}
