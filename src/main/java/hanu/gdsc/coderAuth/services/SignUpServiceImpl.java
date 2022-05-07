package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coder.services.CreateCoderService;
import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

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
        Password newPassword = new Password(password);
        if (userRepository.getByUsername(new Username(username)) == null &&
                userRepository.getByEmail(new Email(email)) == null) {
            boolean registrationConfirmed = false;
            User user = new User(newEmail, newUsername,
                    newPassword, createCoderService.create(), registrationConfirmed);

            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] digest = md.digest();
                String myHash = DatatypeConverter
                        .printHexBinary(digest).toUpperCase();
                        user.setPassword(new Password(myHash));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userRepository.save(user);
        } else {
            throw new BusinessLogicError("Username/email existed", "EXISTED");
        }
    }
}
