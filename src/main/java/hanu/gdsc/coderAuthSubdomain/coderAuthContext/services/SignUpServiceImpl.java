package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.coderSubdomain.coderContext.services.CreateCoderService;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.HashedPassword;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Username;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.ExistedUsernameOrEmail;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateCoderService createCoderService;

    @Override
    public void signUpService(String email, String username, String password) {
        Email newEmail = new Email(email);
        Username newUsername = new Username(username);
        HashedPassword newPassword = HashedPassword.fromRawPassword(password);
        if (userRepository.getByUsername(new Username(username)) == null &&
                userRepository.getByEmail(new Email(email)) == null) {
            User user = User.createUser(newEmail, newUsername, newPassword, createCoderService.create());
                    user.setPassword(newPassword);
            userRepository.save(user);
        } else {
            throw new ExistedUsernameOrEmail();
        }
    }
}
