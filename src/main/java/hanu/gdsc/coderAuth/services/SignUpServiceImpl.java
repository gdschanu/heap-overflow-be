package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.coder.services.CreateCoderService;
import hanu.gdsc.coderAuth.errors.ExistedUsernameOrEmail;

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
