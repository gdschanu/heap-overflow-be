package hanu.gdsc.domain.coderAuth.services.password;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.HashedPassword;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.exceptions.WrongCodeException;
import hanu.gdsc.domain.coderAuth.repositories.ForgotPasswordCodeRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
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
