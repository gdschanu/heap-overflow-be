package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.exceptions.WrongCodeException;
import hanu.gdsc.coderAuth.repositories.forgotPasswordCode.ForgotPasswordCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordCodeRepository forgotPasswordCodeRepository;

    public void confirmForgotPassword(String email, String code, String newPassword) throws NotFoundException, InvalidInputException, WrongCodeException {
        User user = userRepository.getByEmail(new Email(email));
        if (user == null) {
            throw new NotFoundException("Unknown email");
        }
        String codeFromDb = forgotPasswordCodeRepository.getByCoderId(user.getId()).getCode();
        if (!code.equals(codeFromDb)) {
            throw new WrongCodeException();
        }
        user.setPassword(HashedPassword.fromRawPassword(newPassword));
        userRepository.save(user);
    }
}
